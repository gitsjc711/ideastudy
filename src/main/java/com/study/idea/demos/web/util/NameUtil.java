package com.study.idea.demos.web.util;

import com.study.idea.demos.web.dao.*;
import com.study.idea.demos.web.entity.*;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.entity.VO.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NameUtil {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;
    public String ChangeIdToName(Course course){
        Course dbCourse;
        if(course.getId()!=0){
            dbCourse = courseMapper.findById(course.getId());
            if(dbCourse==null){
                return null;
            }else {
                return dbCourse.getName();
            }
        }else {
            return null;
        }
    }
    public String ChangeIdToName(User user){
        User dbUser;
        if(user.getId()!=0){
            dbUser = userMapper.findById(user.getId());
            if(dbUser==null){
                return null;
            }else {
                return dbUser.getAccount();
            }
        }else {
            return null;
        }
    }
    public String ChangeIdToNickname(User user){
        User dbUser;
        if(user.getId()!=0){
            dbUser = userMapper.findById(user.getId());
            if(dbUser==null){
                return null;
            }else {
                return dbUser.getNickname();
            }
        }else {
            return null;
        }
    }
    public String ChangeIdToName(Homework homework){
        Homework dbHomework;
        if(homework.getId()!=0){
            dbHomework=homeworkMapper.findById(homework.getId());
            if(dbHomework==null){
                return null;
            }else {
                return dbHomework.getName();
            }
        }else{
            return null;
        }
    }
    public int changeNameToId(Course course){
        Course dbCourse;
        if(course.getName()!=null){
            dbCourse = courseMapper.findByName(course);
            if(dbCourse==null){
                return 0;
            }else {
                return dbCourse.getId();
            }
        }else {
            return 0;
        }
    }
    public int changeNameToId(User user){
        User dbUser;
        if(user.getAccount()!=null){
            dbUser = userMapper.findByAccount(user);
            if(dbUser==null){
                return 0;
            }else {
                return dbUser.getId();
            }
        }else {
            return 0;
        }
    }
    public List<Integer> changeNickNameToId(User user){
        List<User> dbUser;
        if(user.getNickname()!=null){
            dbUser = userMapper.findByNickname(user);
            if(dbUser==null){
                return null;
            }else {
                List<Integer> idList = new java.util.ArrayList<>();
                for(User u:dbUser){
                    idList.add(u.getId());
                }
                return idList;
            }
        }
        return null;
    }
    public int changeNameToId(Category category){
        Category dbCategory;
        if(category.getCategoryName()!=null){
            dbCategory = categoryMapper.findByName(category);
            if(dbCategory==null){
                return 0;
            }else {
                return dbCategory.getId();
            }
        }else {
            return 0;
        }
    }

}
