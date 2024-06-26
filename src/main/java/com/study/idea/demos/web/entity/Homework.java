package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Homework {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用2待发布
    String name;
    String description;
    int courseId;
    int chapterOrder;
}
