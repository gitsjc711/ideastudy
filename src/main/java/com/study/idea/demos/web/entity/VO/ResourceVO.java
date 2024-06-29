package com.study.idea.demos.web.entity.VO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
@Data
public class ResourceVO {
    int id;
    String label;
    String type;
    String url;
}
