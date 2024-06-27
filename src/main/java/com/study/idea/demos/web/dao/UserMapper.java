package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select id,status,account,salt,password,nickname,email,role,user_avatar as userAvatar from user where account = #{account}")
    User findByAccount(User user);
    @Select("select id,status,account,salt,password,nickname,email,role,user_avatar as userAvatar from user where email = #{email}")
    User findByEmail(User user);
    @Select("select status,account,password,nickname,email,role,user_avatar as userAvatar from user where id=#{id}")
    User findById(int id);
    @Insert("insert into user(create_time,update_time,status,account,salt,password,nickname,email,role,user_avatar) " +
            "values(#{createTime},#{updateTime},#{status},#{account},#{salt},#{password},#{nickname},#{email},#{role},#{userAvatar})")
    boolean insert(User user);
    @Update("update user set update_time=#{updateTime},status=#{status},password=#{password},nickname=#{nickname},email=#{email},role=#{role},user_avatar=#{userAvator} where account=#{account}")
    boolean update(User user);
}
