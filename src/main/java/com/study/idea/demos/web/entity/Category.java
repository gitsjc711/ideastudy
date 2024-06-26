package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Category {
    private int id;
    private Date createTime;
    private Date updateTime;
    private int status;//0正常1禁用
    private String categoryName;
    private String categoryLogo;
    private int parentId;
}
