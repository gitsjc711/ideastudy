package com.study.idea.demos.web.entity.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
public class CourseDTO {
    int id;
    int status;//0正常1禁用2待发布
    String name;
    String description;
    String courseLogo;
    double price;
    int teacherId;
    int categoryId;
    MultipartFile file;
}
