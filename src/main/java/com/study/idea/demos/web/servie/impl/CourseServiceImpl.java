package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CategoryMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.servie.CourseService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private NameUtil nameUtil;
    @Override
    public Course findByName(Course course) {
        if(course.getName()==null){
            return null;
        }
        return courseMapper.findByName(course);
    }
    @Override
    public StatusUtil.ErrorCode check(Course course){
        return null;
    }
    @Override
    public List<Course> findByCategoryId(Category category) {
        if(category.getId()==0){
            return null;
        }
        List<Course> lists=courseMapper.findByCategoryId(category.getId());
        Iterator<Course> iterator=lists.iterator();
        while(iterator.hasNext()){
            Course i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Course course) {
        if(course.getName()==null||course.getTeacherId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(courseMapper.findByName(course)!=null){
            return StatusUtil.ErrorCode.ALREADY_EXISTS;
        }
        User teacher =userMapper.findById(course.getTeacherId());
        if(teacher==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }else if(teacher.getRole()!=2){
            return StatusUtil.ErrorCode.NO_PERMISSION;
        }
        course.setStatus(2);
        Date date = new Date();
        course.setCreateTime(date);
        course.setUpdateTime(date);
        if(courseMapper.insert(course)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode publish(Course course){
        Course dbCourse = courseMapper.findByName(course);
        if(dbCourse==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(dbCourse.getCourseLogo()==null&&course.getCourseLogo()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }else if(course.getCourseLogo()==null){
            course.setCourseLogo(dbCourse.getCourseLogo());
        }
        if(dbCourse.getDescription()==null&&course.getDescription()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }else if(course.getDescription()==null){
            course.setDescription(dbCourse.getDescription());
        }
        if(dbCourse.getCategoryId()==0&&course.getCategoryId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }else if(course.getCategoryId()==0){
            course.setCategoryId(dbCourse.getCategoryId());
        }
        if(categoryMapper.findById(course.getCategoryId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        Date date = new Date();
        course.setUpdateTime(date);
        course.setStatus(0);
        if(courseMapper.update(course)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    public List<CourseVO> changeToVO(List<Course> courses){
        List<CourseVO> result = new ArrayList<>();
        for(Course course:courses){
            CourseVO courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setStatus(course.getStatus());
            courseVO.setName(course.getName());
            courseVO.setDescription(course.getDescription());
            courseVO.setPrice(course.getPrice());
            courseVO.setCourseLogo(course.getCourseLogo());
            Category category = categoryMapper.findById(course.getCategoryId());
            courseVO.setCategoryName(category.getCategoryName());
            User teacher = userMapper.findById(course.getTeacherId());
            courseVO.setTeacherName(teacher.getNickname()+"老师");
            result.add(courseVO);
        }
        return result;
    };
}
