package com.study.idea.demos.web.servie.impl;

import com.alipay.api.domain.UserVo;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.DTO.UserDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.UserVO;
import com.study.idea.demos.web.servie.UserService;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.MD5Util;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UrlUtil urlUtil;
    @Override
    public StatusUtil.ErrorCode checkPram(User user){
        if(user == null|| user.getAccount() == null|| user.getPassword() == null||user.getEmail()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        User dbUser=userMapper.findByAccount(user);
        if(dbUser!=null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        dbUser=userMapper.findByEmail(user);
        if(dbUser!=null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }
    @Override
    public StatusUtil.ErrorCode checkLogin(User user){//登录验证
        if(user.getAccount() == null|| user.getPassword() == null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        User dbUser=userMapper.findByAccount(user);
        if(dbUser==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if(dbUser.getStatus()==1){
            return StatusUtil.ErrorCode.BANNED;
        }
        String psw=MD5Util.inputPassToDBPass(user.getPassword(),dbUser.getSalt());
        if (psw.equals(dbUser.getPassword())) {
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode register(User user){
        Date date=new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        user.setStatus(0);
        String salt=MD5Util.generateSalt(user.getAccount());
        user.setSalt(salt);
        user.setPassword(MD5Util.inputPassToDBPass(user.getPassword(),salt));
        if(user.getNickname()==null){
            user.setNickname(user.getAccount());
        }
        if(userMapper.insert(user)) {
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode update(User user){
        if(user == null|| user.getAccount() == null|| user.getPassword() == null||user.getEmail()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        User dbUser=userMapper.findByEmail(user);
        if(dbUser!=null&&!dbUser.getAccount().equals(user.getAccount())){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }

        dbUser=userMapper.findByAccount(user);
        if(dbUser==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        String psw=MD5Util.inputPassToDBPass(user.getPassword(),dbUser.getSalt());
        if(!psw.equals(dbUser.getPassword())) {
            user.setPassword(psw);
        }else {
            user.setPassword(dbUser.getPassword());
        }
        if(dbUser.getRole()!=0){//权限验证
            if(user.getRole()==0){
                return StatusUtil.ErrorCode.NO_PERMISSION;
            }
            if(user.getStatus()==1){
                return StatusUtil.ErrorCode.NO_PERMISSION;
            }
        }
        if(user.getNickname()==null){
            user.setNickname(user.getAccount());
        }
        Date date=new Date();
        user.setUpdateTime(date);
        if(userMapper.update(user)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    public User showDetails(User user){
        if(user == null|| user.getAccount() == null){
            return null;
        }
        User dbUser=userMapper.findByAccount(user);
        if(dbUser==null){
            return null;
        }
        if(dbUser.getRole()!=0) {
            dbUser.setSalt(null);
            dbUser.setPassword(null);
        }
        return dbUser;
    }
    public User changeToEntity(UserDTO userDTO){
        User user=new User();
        user.setAccount(userDTO.getAccount());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        switch (userDTO.getRole()){
            case "student":
                user.setRole(1);
                break;
            case "teacher":
                user.setRole(2);
                break;
        }
        return user;
    }
    public UserVO changeToVO(User user){
        UserVO userVO=new UserVO();
        userVO.setId(user.getId());
        userVO.setAccount(user.getAccount());
        userVO.setEmail(user.getEmail());
        userVO.setNickname(user.getNickname());
        String role="";
        switch (user.getRole()){
            case 0:
                role="管理员";
                break;
            case 1:
                role="学生";
                break;
            case 2:
                role="教师";
                break;
            default:
                role="未知";
        }
        userVO.setRole(role);
        userVO.setStatus(user.getStatus());
        userVO.setUserAvatarRequestUrl(urlUtil.changeToRequestUrl(user.getUserAvatar()));
        return userVO;
    }
}
