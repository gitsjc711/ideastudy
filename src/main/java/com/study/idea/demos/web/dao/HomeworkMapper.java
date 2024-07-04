package com.study.idea.demos.web.dao;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.HomeworkStudent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HomeworkMapper {

    @Select("select id,status,name,description,course_id as courseId, chapter_order as chapterOrder from homework where course_id=#{courseId}")
    List<Homework> findByCourseId(int courseId);
    @Select("select id,status,name,description,course_id as courseId, chapter_order as chapterOrder from homework where id=#{id}")
    Homework findById(int id);
    @Select("select id,status,name,description,course_id as courseId, chapter_order as chapterOrder from homework where chapter_order=#{chapterOrder} and course_id=#{courseId}")
    List<Homework> findByCourseIdAndChapterOrder(Homework homework);
    @Insert("insert into homework(create_time, update_time, status, name, description, course_id, chapter_order) " +
            "values (#{createTime},#{updateTime},#{status},#{name},#{description},#{courseId},#{chapterOrder})")
    boolean insert(Homework homework);
    @Delete("delete from homework where id=#{id}")
    boolean delete(int id);
    @Select("select id, update_time as updateTime,status, user_id as userId, homework_id as homeworkId, homework_url as homeworkUrl , homework_type as homeworkType from homework_student where homework_id=#{homeworkId}")
    List<HomeworkStudent> findByHomeworkId(int homeworkId);
    @Select("select id, update_time as updateTime,status, user_id as userId, homework_id as homeworkId, homework_url as homeworkUrl , homework_type as homeworkType from homework_student where homework_id=#{homeworkId} and user_id=#{userId}")
    HomeworkStudent findByHomeworkIdAndUserId(HomeworkStudent homeworkStudent);
    @Insert("insert into homework_student(create_time, update_time, status, user_id, homework_id, homework_url, homework_type)" +
            "values (#{createTime},#{updateTime},#{status},#{userId},#{homeworkId},#{homeworkUrl},#{homeworkType})")
    boolean insertFinishHomework(HomeworkStudent homeworkStudent);
    @Update("update homework_student set status=#{status},update_time=#{updateTime},homework_url=#{homeworkUrl},homework_type=#{homeworkType} where homework_id=#{homeworkId} and user_id=#{userId}")
    boolean updateFinishHomework(HomeworkStudent homeworkStudent);
    @Delete("delete from homework_student where homework_id=#{homeworkId}")
    boolean deleteByHomeworkId(int homeworkId);


}
