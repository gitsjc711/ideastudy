package com.study.idea.demos.web.entity.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserDTO {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用
    String account;
    String salt;
    String password;
    String nickname;
    String email;
    int role;//0管理员1学生2老师
    int code;
    MultipartFile file;
}
