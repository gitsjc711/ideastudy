package com.study.idea.demos.web.entity.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class UserDTO {
    int id;
    String account;
    String password;
    String nickname;
    String email;
    String role;//0管理员1学生2老师
    String code;
    MultipartFile avatar;
}
