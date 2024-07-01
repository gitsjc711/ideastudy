package com.study.idea.demos.web.entity.DTO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
public class ResourceDTO {
    int id;
    int status;//0正常1禁用
    String name;
    String type;
    String url;
    int courseId;
    int chapterOrder;
}
