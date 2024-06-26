package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;
@Data
public class CourseVO {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用2待发布
    String name;
    String description;
    String courseLogo;
    double price;
    String teacherName;
    String categoryName;
}
