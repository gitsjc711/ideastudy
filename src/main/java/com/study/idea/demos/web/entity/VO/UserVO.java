package com.study.idea.demos.web.entity.VO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
public class UserVO {
    int id;
    int status;//0正常1禁用
    String account;
    String nickname;
    String email;
    String role;//0管理员1学生2老师
}
