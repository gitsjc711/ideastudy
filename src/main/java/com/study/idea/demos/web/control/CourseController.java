package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
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
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(CourseDTO courseDTO){
        if(courseDTO.getFile()!=null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Course course = new Course();
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setPrice(courseDTO.getPrice());
        course.setTeacherId(courseDTO.getTeacherId());
        course.setCategoryId(courseDTO.getCategoryId());

        return courseService.insert(course);
    }
    @RequestMapping("/publish")
    @ResponseBody
    public StatusUtil.ErrorCode publish(@RequestBody Course course){
        return courseService.publish(course);
    }
    @RequestMapping("/findByCategory")
    @ResponseBody
    public List<CourseVO> findByCategoryId(@RequestBody Category category){
        List<Course> lists = courseService.findByCategoryId(category);
        return  courseService.changeToVO(lists);
    }

}
