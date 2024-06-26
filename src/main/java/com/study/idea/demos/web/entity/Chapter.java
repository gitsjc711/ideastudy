package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Chapter {
    private int id;
    private Date createTime;
    private Date updateTime;
    private int status;//0正常1禁用
    private int chapterOrder;
    private String name;
    private int courseId;
}
