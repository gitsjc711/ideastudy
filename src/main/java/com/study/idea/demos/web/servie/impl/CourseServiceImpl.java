package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CategoryMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.OrderMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.Order;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.servie.CourseService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import com.sun.org.apache.regexp.internal.RE;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
    private OrderMapper orderMapper;
    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private NameUtil nameUtil;

    @Override
    public StatusUtil.ErrorCode checkPram(Course course){
        if(course.getTeacherId()==0||course.getCategoryId()==0||course.getDescription()==null||course.getName()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }
    @Override
    public Course findByName(Course course) {
        if(course.getName()==null){
            return null;
        }
        return courseMapper.findByName(course);
    }
    @Override
    public List<Course> findByCategoryId(Category category) {
        if(category.getId()==0){
            return null;
        }
        List<Course> list=courseMapper.findByCategoryId(category.getId());
        Iterator<Course> iterator=list.iterator();
        while(iterator.hasNext()){
            Course i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return list;
    }
    @Override
    public List<Course> findByTeacherId(User user){
        if(user.getId()==0){
            return null;
        }
        List<Course> list=courseMapper.findByTeacherId(user.getId());
        Iterator<Course> iterator=list.iterator();
        while(iterator.hasNext()){
            Course i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return list;
    }

    @Override
    public List<Course> findAll(User user){
        List<Course> list=courseMapper.findAll();
        Iterator<Course> iterator=list.iterator();
        while(iterator.hasNext()){
            Course i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return list;
    }
    @Override
    public List<Course> exceptBuy(List<Course> list,User user){
        if(list==null){
            return null;
        }
        List<Order> orders=orderMapper.findByUserId(user.getId());
        Iterator<Course> iterator=list.iterator();
        while(iterator.hasNext()){
            Course i = iterator.next();
            for(Order order:orders){
                if(order.getCourseId()==i.getId()){
                    iterator.remove();
                }
            }
        }
        return list;
    }
    @Override
    public List<Course> onlyBuy(List<Course> list,User user){
        if(list==null){
            return null;
        }
        List<Order> orders=orderMapper.findByUserId(user.getId());
        Iterator<Course> iterator=list.iterator();
        List<Course> result=new ArrayList<>();
        while(iterator.hasNext()){
            Course i = iterator.next();
            for(Order order:orders){
                if(order.getCourseId()==i.getId()){
                    result.add(i);
                }
            }
        }
        return result;
    }


    @Override
    public StatusUtil.ErrorCode insert(Course course) {
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
        if(courses==null){
            return null;
        }
        List<CourseVO> result = new ArrayList<>();
        for(Course course:courses){
            CourseVO courseVO = new CourseVO();
            courseVO.setId(course.getId());
            courseVO.setName(course.getName());
            courseVO.setDescription(course.getDescription());
            courseVO.setPrice(course.getPrice());
            courseVO.setCourseLogo(course.getCourseLogo());
            String url = urlUtil.changeToRequestUrl(course.getCourseLogo());
            courseVO.setCourseLogoRequestUrl(url);
            Category category = categoryMapper.findById(course.getCategoryId());
            courseVO.setCategoryName(category.getCategoryName());
            User teacher = userMapper.findById(course.getTeacherId());
            courseVO.setTeacherName(teacher.getNickname()+"老师");
            result.add(courseVO);
        }
        return result;
    }
    public Course changeToEntity(CourseDTO courseDTO){
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setTeacherId(courseDTO.getTeacherId());
        Category category=new Category();
        category.setCategoryName(courseDTO.getCategoryName());
        course.setCategoryId(nameUtil.changeNameToId(category));
        return course;
    }

}
