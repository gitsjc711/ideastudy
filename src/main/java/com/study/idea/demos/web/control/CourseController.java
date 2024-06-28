package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.servie.CourseService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private NameUtil nameUtil;
    @Autowired
    private UrlUtil urlUtil;
    @Autowired
    private CourseService courseService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(CourseDTO courseDTO){
        Course course = courseService.changeToEntity(courseDTO);
        if(!courseDTO.getFile().isEmpty()) {
            String dirUrl = urlUtil.getUrl(courseDTO);
            String writeUrl = dirUrl + "\\" + courseDTO.getFile().getOriginalFilename();
            File dir=new File(dirUrl);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File dest=new File(writeUrl);
            course.setCourseLogo(writeUrl);
            try {
                courseDTO.getFile().transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return courseService.insert(course);
    }
    @RequestMapping("/publish")
    @ResponseBody
    public StatusUtil.ErrorCode publish(CourseDTO courseDTO){
        Course course = courseService.changeToEntity(courseDTO);
        if(!courseDTO.getFile().isEmpty()){
            String dirUrl = urlUtil.getUrl(courseDTO);
            String writeUrl = dirUrl + "\\" + courseDTO.getFile().getOriginalFilename();
            course.setCourseLogo(writeUrl);
            File dir=new File(dirUrl);
            if(!dir.exists()){
                dir.mkdirs();
            }
            File dest=new File(writeUrl);
            try {
                courseDTO.getFile().transferTo(dest);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        StatusUtil.ErrorCode code= courseService.checkPram(course);
        if(code!=StatusUtil.ErrorCode.OK){
            return code;
        }
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
        category.setId(nameUtil.changeNameToId(category));
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
        user.setAccount(teacherName);
        user.setId(nameUtil.changeNameToId(user));
        List<Course> list = courseService.findByTeacherId(user);
        List<Course> lists = courseService.exceptBuy(list,user);
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

}
