package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.DTO.UserDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.UserVO;
import com.study.idea.demos.web.servie.UserService;
import com.study.idea.demos.web.util.RedisUtil;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MailSender mailSender;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UrlUtil urlUtil;
    @Value("${spring.mail.username}")
    private String username;
    @RequestMapping("/login")
    @ResponseBody
    public StatusUtil.ErrorCode login(@RequestBody User user)//登录
    {
        return userService.checkLogin(user);
    }
    @RequestMapping("/register")
    @ResponseBody
    public StatusUtil.ErrorCode register(UserDTO userDTO)//注册
    {
        User user = userService.changeToEntity(userDTO);
        StatusUtil.ErrorCode errorCode=userService.checkPram(user);
        if(errorCode!=StatusUtil.ErrorCode.OK){
            return errorCode;
        }
        if(userDTO.getCode()==null||userDTO.getAvatar()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Object object=redisUtil.get(user.getEmail());
        if(object==null){//验证码失效
            return StatusUtil.ErrorCode.TIMEOUT;
        }
        String code = String.valueOf(object);
        if(userDTO.getCode().equals(code)) {//验证码正确
            redisUtil.delete(user.getEmail());
            return userService.register(user);
        }else{
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
    }
    @RequestMapping("/user/update")
    @ResponseBody
    public StatusUtil.ErrorCode update(@RequestBody UserDTO userDTO)//用户修改自己的信息
    {
        User user = userService.changeToUpdateEntity(userDTO);
        return userService.update(user,userDTO.getCode());
    }
    @RequestMapping("/user/showDetails")
    @ResponseBody
    public UserVO showDetails(@RequestBody User user)
    {
        User dbUser = userService.showDetails(user);
        if(dbUser==null){
            return null;
        }
        dbUser.setUserAvatar(urlUtil.changeToRequestUrl(dbUser.getUserAvatar()));
        return userService.changeToVO(dbUser);
    }
    @RequestMapping("/sendMail")
    @ResponseBody
    public StatusUtil.ErrorCode sendMail(@RequestBody  User user)//发送验证码
    {
        if(user.getEmail() == null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Object object=redisUtil.get(user.getEmail());
        if(object!=null){
            return StatusUtil.ErrorCode.TOO_MANY_REQUESTS;
        }
        Random random = new Random();
        int code=random.nextInt(999999);
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(user.getEmail());
        message.setSubject("验证码");
        message.setText("验证码为："+code);
        mailSender.send(message);
        redisUtil.set(user.getEmail(),code);
        return StatusUtil.ErrorCode.OK;
    }
}
