package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.servie.CourseService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private NameUtil nameUtil;
    @Autowired
    private CourseService courseService;
    @RequestMapping("/publish")
    @ResponseBody
    public StatusUtil.ErrorCode publish(CourseDTO courseDTO){
        Course course = courseService.changeToEntity(courseDTO);
        Category category = new Category();
        category.setCategoryName(courseDTO.getCategory());
        int categoryId=nameUtil.changeNameToId(category);
        if(categoryId==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        course.setCategoryId(categoryId);
        course.setCourseLogo(courseDTO.getImageUrl());
        StatusUtil.ErrorCode code= courseService.checkPram(course);
        if(code!=StatusUtil.ErrorCode.OK){
            return code;
        }
        if(courseDTO.getImageUrl()==null) {
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        courseService.insert(course);
        return courseService.publish(course);
    }
    @RequestMapping("/findByCategory")
    @ResponseBody
    public List<CourseVO> findByCategory(String categoryName,int uid){
        if(categoryName==null||uid==0){
            return null;
        }
        Category category = new Category();
        category.setCategoryName(categoryName);
        int categoryId = nameUtil.changeNameToId(category);
        if(categoryId==0){
            return null;
        }
        category.setId(categoryId);
        List<Course> list = courseService.findByCategoryId(category);
        User user = new User();
        user.setId(uid);
        List<Course> lists = courseService.exceptBuy(list,user);
        return  courseService.changeToVO(lists);
    }
    @RequestMapping("/findByTeacher")
    @ResponseBody
    public List<CourseVO> findByTeacher(String teacherName,int uid){
        if(teacherName==null||uid==0){
            return null;
        }
        User user = new User();
        user.setNickname(teacherName);
        List<Integer> userId=nameUtil.changeNickNameToId(user);
        if(userId==null){
            return null;
        }
        List<Course> list = new ArrayList<>();
        for(Integer id:userId){
            User teacher = new User();
            teacher.setId(id);
            list.addAll(courseService.findByTeacherId(teacher));
        }
        User student= new User();
        student.setId(uid);
        List<Course> lists = courseService.exceptBuy(list,student);
        return courseService.changeToVO(lists);
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<CourseVO> findAll(@RequestBody User user){
        List<Course> list =courseService.findAll(user);
        List<Course> lists=courseService.exceptBuy(list,user);
        return courseService.changeToVO(lists);
    }
    @RequestMapping("/findMyCourse")
    @ResponseBody
    public List<CourseVO> findMyCourse(@RequestBody User user){
        if (user.getId()==0){
            return null;
        }
        List<Course> list =courseService.findAll(user);
        List<Course> lists=courseService.onlyBuy(list,user);
        return courseService.changeToVO(lists);

    }
    @RequestMapping("/findMyTeach")
    @ResponseBody
    public List<CourseVO> findMyTeach(@RequestBody User user){
        if (user.getId()==0){
            return null;
        }
        List<Course> list =courseService.findByTeacherId(user);
        return courseService.changeToVO(list);
    }

}
