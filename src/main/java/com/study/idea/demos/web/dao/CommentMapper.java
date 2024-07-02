package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("select id,status,content,reply_id as replyId,user_id as userId,course_id as courseId from comment where reply_id=#{replyId}")
    List<Comment> findByReplyId(int replyId);
    @Select("select id,status,content,reply_id as replyId,user_id as userId,course_id as courseId from comment where id=#{id}")
    Comment findById(int id);
    @Select("select id,update_time as updateTime, status, content, reply_id as replyId, user_id as userId, course_id as courseId from comment where course_id=#{courseId}")
    List<Comment> findByCourseId(int courseId);
    @Insert("insert into comment(create_time, update_time, status, content, reply_id, user_id, course_id) " +
            "VALUES (#{createTime},#{updateTime},#{status},#{content},#{replyId},#{userId},#{courseId})")
    boolean insert(Comment comment);

}
