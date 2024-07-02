package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Progress {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常
    int userId;
    int resourceId;
}
