package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.OrderMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Order;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.servie.AlipayService;
import com.study.idea.demos.web.util.StatusUtil;
import org.bouncycastle.crypto.tls.PRFAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public  StatusUtil.ErrorCode checkOrder(Order order){
        if(order.getOrderNo()==null||order.getCourseId()==0||order.getUserId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Course course=courseMapper.findById(order.getCourseId());
        User user=userMapper.findById(order.getUserId());
        if(course==null||user==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if(course.getPrice()!=order.getActualPrice()){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(orderMapper.findByOrderNo(order.getOrderNo())!=null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }

    @Override
    public StatusUtil.ErrorCode add(Order order) {
        Date date=new Date();
        order.setCreateTime(date);
        order.setUpdateTime(date);
        order.setStatus(2);
        if(orderMapper.insert(order)){
            return StatusUtil.ErrorCode.OK;
        }else {
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }

    @Override
    public StatusUtil.ErrorCode pay(Order order) {
        Date date=new Date();
        order.setUpdateTime(date);
        order.setStatus(0);
        if(orderMapper.update(order)){
            return StatusUtil.ErrorCode.OK;
        }else {
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }

    @Override
    public List<Order> findByUserId(User user) {
        return orderMapper.findByUserId(user.getId());
    }
}
