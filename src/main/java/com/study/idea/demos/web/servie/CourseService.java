package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CourseService {
    Course findByName(Course course);
    StatusUtil.ErrorCode checkPram(Course course);
    List<Course> findByCategoryId(Category category);
    List<Course> findByTeacherId(User user);
    List<Course> exceptBuy(List<Course> list,User user);
    List<Course> onlyBuy(List<Course> list,User user);
    List<Course> findAll(User user);

    StatusUtil.ErrorCode insert(Course course);
    StatusUtil.ErrorCode publish(Course course);
    List<CourseVO> changeToVO(List<Course> courses);
    Course changeToEntity(CourseDTO courseDTO);
}
