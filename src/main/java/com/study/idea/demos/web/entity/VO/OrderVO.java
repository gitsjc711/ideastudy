package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;
@Data
public class OrderVO {
    int id;
    Date createTime;
    Date updateTime;
    int status;//0正常1禁用2待支付
    String orderNo;
    String courseName;
    String userName;
    double actualPrice;
}
