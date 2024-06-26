package com.study.idea.demos.web.control;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/order")
public class AlipayController {
    @Autowired
    AlipayService alipayService;
    @Autowired
    NameUtil nameUtil;
    @RequestMapping("/add")//添加订单
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Order order)
    {
        StatusUtil.ErrorCode check=alipayService.checkOrder(order);
        if(check!=StatusUtil.ErrorCode.OK)
        {
            return check;
        }
        return alipayService.add(order);
    }
    @RequestMapping("/pay")//支付
    @ResponseBody
    public StatusUtil.ErrorCode pay(Order order, HttpServletResponse response) throws IOException {
        StatusUtil.ErrorCode check=alipayService.checkPay(order);
        if(check!=StatusUtil.ErrorCode.OK)
        {
            return check;
        }
        Course course=new Course();
        course.setId(order.getCourseId());
        String courseName=nameUtil.ChangeIdToName(course);
        User user=new User();
        user.setId(order.getUserId());
        String userName=nameUtil.ChangeIdToName(user);
        if(courseName==null||userName==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
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
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
            // 处理异常，返回错误信息给前端
        }
        response.setContentType("text/html;charset=" +  AlipayConfig.charset);
        // 直接将完整的表单html输出到页面
        response.getWriter().write(form);
        response.getWriter().flush();
        response.getWriter().close();
        return StatusUtil.ErrorCode.OK;
    }

}
