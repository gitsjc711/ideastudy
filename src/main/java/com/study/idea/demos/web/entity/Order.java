package com.study.idea.demos.web.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Order {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用2待支付
    String orderNo;
    int courseId;
    int userId;
    double actualPrice;
}
