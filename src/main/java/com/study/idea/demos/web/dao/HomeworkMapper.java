package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Homework;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    @Select("select id,status,name,description,course_id as courseId, chapter_order as chapterOrder from homework where course_id=#{courseId}")
    List<Homework> findByCourseId(int courseId);
    @Insert("insert into homework(create_time, update_time, status, name, description, course_id, chapter_order) " +
            "values (#{createTime},#{updateTime},#{status},#{name},#{description},#{courseId},#{chapterOrder})")
    boolean insert(Homework homework);
}
