package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Notice {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用
    int courseId;
    int teacherId;
    String title;
    String content;
}
