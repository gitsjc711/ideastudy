package com.study.idea.demos.web.entity.DTO;

import lombok.Data;

import java.util.Date;
@Data
public class CategoryDTO {
    private int id;
    private int status;//0正常1禁用
    private String categoryName;
    private int parentId;
    private String role;
}
