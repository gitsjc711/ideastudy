package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;
@Data
public class CourseVO {
    int id;
    String name;
    String description;
    String courseLogo;
    String courseLogoRequestUrl;
    double price;
    String teacherName;
    String categoryName;
}
