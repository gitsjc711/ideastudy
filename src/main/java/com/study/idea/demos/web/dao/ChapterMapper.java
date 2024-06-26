package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Chapter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ChapterMapper {
    @Select("select id,status,chapter_order as chapterOrder,name,course_id as courseId from chapter where course_id=#{courseId}")
    List<Chapter> findByCourseId(int courseId);
    @Select("select id,status,chapter_order as chapterOrder,name,course_id as courseId from chapter where id=#{id}")
    Chapter findById(int id);
    @Insert("insert into chapter(create_time, update_time, status, chapter_order, name, course_id) values(#{createTime},#{updateTime},#{status},#{chapterOrder},#{name},#{courseId})")
    boolean insert(Chapter chapter);
    @Update("update chapter set update_time=#{updateTime},status=#{satus},chapter_order=#{chapterOrder},name=#{name},course_id=#{course_id} where id=#{id}")
    boolean update(int id);
}
