package com.study.idea.demos.web.entity.VO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
@Data
public class ResourceVO {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用
    String name;
    String type;
    String url;
    String courseName;
    String chapterName;
    String requestUrl;
}
