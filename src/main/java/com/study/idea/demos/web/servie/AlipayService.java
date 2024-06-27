package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Order;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface AlipayService {
    StatusUtil.ErrorCode checkOrder(Order order);
    StatusUtil.ErrorCode add(Order order);
    StatusUtil.ErrorCode pay(Order order);
    List<Order> findByUserId(User user);
}
