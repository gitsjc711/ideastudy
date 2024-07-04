package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Select("select id,status,order_no as orderNo,course_id as courseId,user_id as userId,actual_price as actualPrice from orders where order_no=#{orderNo}")
    Order findByOrderNo(String no);
    @Select("select id,status,order_no as orderNo,course_id as courseId,user_id as userId,actual_price as actualPrice from orders where user_id=#{userId}")
    List<Order> findByUserId(int userId);
    @Insert("insert into orders(create_time, update_time, status, order_no, course_id, user_id, actual_price) " +
            "VALUES (#{createTime},#{updateTime},#{status},#{orderNo},#{courseId},#{userId},#{actualPrice})")
    boolean insert(Order order);
    @Update("update orders set update_time=#{updateTime},status=#{status} where order_no=#{orderNo}")
    boolean update(Order order);

}
