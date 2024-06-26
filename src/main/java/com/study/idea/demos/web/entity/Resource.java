package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Resource {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用
    String name;
    String type;
    String url;
    int chapterId;
}
