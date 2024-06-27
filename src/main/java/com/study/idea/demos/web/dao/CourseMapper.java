package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Course;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CourseMapper {
    @Select("select id,name,description,status,course_logo as courseLogo,price,teacher_id as teacherId,category_id as categoryId from course where name=#{name}")
    Course findByName(Course course);
    @Select("select id,status,name,description,course_logo as courseLogo,price,teacher_id as teacherId,category_id as categoryId from course where id=#{id}")
    Course findById(int id);
    @Select("select id,status,name,description,course_logo as courseLogo,price,teacher_id as teacherId,category_id as categoryId from course")
    List<Course> findAll();
    @Select("select id,status,name,description,course_logo as courseLogo,price,teacher_id as teacherId,category_id as categoryId from course where category_id=#{categoryId}")
    List<Course> findByCategoryId(int categoryId);
    @Select("select id,status,name,description,course_logo as courseLogo,price,teacher_id as teacherId,category_id as categoryId from course where teacher_id=#{teacherId}")
    List<Course> findByTeacherId(int teacherId);
    @Insert("insert into course(create_time,update_time,status,name,description,course_logo,price,teacher_id,category_id) values(#{createTime},#{updateTime},#{status},#{name},#{description},#{courseLogo},#{price},#{teacherId},#{categoryId})")
    boolean insert(Course course);
    @Update("update course set update_time=#{updateTime},status=#{status},description=#{description},course_logo=#{courseLogo},price=#{price},teacher_id=#{teacherId},category_id=#{categoryId} where name=#{name}")
    boolean update(Course course);
}
