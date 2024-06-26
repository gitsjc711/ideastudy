package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Course {
    private int id;
    private Date createTime;
    private Date updateTime;
    private int status;//0正常1禁用2待发布
    private String name;
    private String description;
    private String courseLogo;
    private double price;
    private int teacherId;
    private int categoryId;
}
