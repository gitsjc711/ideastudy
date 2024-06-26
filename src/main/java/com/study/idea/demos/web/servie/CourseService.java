package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CourseService {
    Course findByName(Course course);
    StatusUtil.ErrorCode check(Course course);
    List<Course> findByCategoryId(Category category);
    StatusUtil.ErrorCode insert(Course course);
    StatusUtil.ErrorCode publish(Course course);
    List<CourseVO> changeToVO(List<Course> courses);
}