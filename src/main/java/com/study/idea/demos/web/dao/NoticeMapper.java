package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Notice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper {
    @Select("select id,status,course_id as courseId,teacher_id as teacherId,title,content from notice where course_id=#{courseId}")
    List<Notice> findByCourseId(int courseId);
    @Insert("insert into notice(create_time, update_time, status, course_id, teacher_id, title, content) " +
            "VALUES(#{createTime},#{updateTime},#{status},#{courseId},#{teacherId},#{title},#{content})")
    boolean insert(Notice notice);
    @Delete("delete from notice where id=#{id}")
    boolean delete(int id);
}
