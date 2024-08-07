package com.study.idea.demos.web.control;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.study.idea.demos.web.config.AlipayConfig;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Order;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.servie.AlipayService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class AlipayController {
    @Autowired
    AlipayService alipayService;
    @Autowired
    NameUtil nameUtil;
    @RequestMapping("/free")
    @ResponseBody
    public StatusUtil.ErrorCode free(@RequestBody Order order){
        StatusUtil.ErrorCode check=alipayService.checkOrder(order);
        if(check!=StatusUtil.ErrorCode.OK)
        {
            return check;
        }
        alipayService.add(order);
        return alipayService.pay(order);
    }
    @RequestMapping("/pay")//支付
    public void pay(Order order, HttpServletResponse response) throws IOException {
        StatusUtil.ErrorCode check=alipayService.checkOrder(order);
        if(check!=StatusUtil.ErrorCode.OK)
        {
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(check.getMessage());
            return ;
        }
        Course course=new Course();
        course.setId(order.getCourseId());
        String courseName=nameUtil.ChangeIdToName(course);
        User user=new User();
        user.setId(order.getUserId());
        String userName=nameUtil.ChangeIdToName(user);
        if(courseName==null||userName==null){
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(StatusUtil.ErrorCode.PARAMETER_ERROR.getMessage());
            return ;
        }
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayurl, AlipayConfig.app_id
                , AlipayConfig.merchant_private_key, "json", AlipayConfig.charset
                , AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        /* 同步通知，支付完成后，支付成功页面*/
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        /* 异步通知，支付完成后，需要进行的异步处理*/
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+order.getOrderNo()+"\"," +
                "    \"subject\":\""+courseName+"\"," +
                "    \"total_amount\":"+order.getActualPrice()+"," +
                "    \"body\":\""+userName+"\"," +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        /* 转换格式*/
        String form = "";

        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(alipayRequest).getBody();
            // 返回给前端
        } catch (AlipayApiException e) {
            return ;
            // 处理异常，返回错误信息给前端
        }
        response.setContentType("text/html;charset=" +  AlipayConfig.charset);
        // 直接将完整的表单html输出到页面
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
    }
    @RequestMapping("/notify")
    public void payNotify(HttpServletRequest request) throws Exception
    {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String courseName=params.get("subject");
            Course course=new Course();
            course.setName(courseName);
            int courseId=nameUtil.changeNameToId(course);
            String userName=params.get("body");
            User user=new User();
            user.setAccount(userName);
            int userId=nameUtil.changeNameToId(user);
            String actualPrice=params.get("total_amount");
            // 支付宝验签
            if (AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type)) {
                // 验签通过
                System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));
                System.out.println("买家账户:"+params.get("body"));
                Order order =new Order();
                order.setOrderNo(tradeNo);
                order.setUserId(userId);
                order.setCourseId(courseId);
                order.setActualPrice(Double.parseDouble(actualPrice));
                alipayService.add(order);
                if(alipayService.pay(order)== StatusUtil.ErrorCode.OK){
                    System.out.println("交易成功");
                }else{
                    System.out.println("交易失败");
                }
            }
        }
    }
    @RequestMapping("/return")
    public String payReturn(HttpServletRequest request) throws Exception
    {
        boolean signVerified=signVerified(request);
        // 同步执行，返回界面
        if (signVerified) {
            System.out.println("AlipayController.returnUrl" + "----前往支付成功页面");
            //todo

            return "redirect:http://www.baidu.com";
        } else {
            System.out.println("AlipayController.returnUrl" + "----前往支付失败页面");
            return "redirect:http://www.baidu.com";
        }
    }
    private boolean signVerified(HttpServletRequest request)throws Exception {
        // 获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            params.put(name, request.getParameter(name));
        }
        return AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); // 调用SDK验证签名
    }
    @RequestMapping("/findByUid")
    @ResponseBody
    public List<Order> findByUid(User user)
    {
        return alipayService.findByUserId(user);
    }


}
