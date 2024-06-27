package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class User {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用
    String account;
    String salt;
    String userAvatar;
    String password;
    String nickname;
    String email;
    int role;//0管理员1学生2老师
}
