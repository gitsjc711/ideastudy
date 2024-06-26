package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;
@Data
public class NoticeVO {
    int id;
    int status;//0正常1禁用
    String course;
    String teacher;
    String title;
    String content;
}
