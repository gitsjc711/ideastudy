package com.study.idea.demos.web.util;

import com.study.idea.demos.web.dao.*;
import com.study.idea.demos.web.entity.*;
import com.study.idea.demos.web.entity.VO.CourseVO;
import com.study.idea.demos.web.entity.VO.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NameUtil {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ChapterMapper chapterMapper;
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
    public String ChangeIdToName(Category category){
        Category dbCategory;
        if(category.getId()!=0){
            dbCategory = categoryMapper.findById(category.getId());
            if(dbCategory==null){
                return null;
            }else {
                return dbCategory.getCategoryName();
            }
        }else {
            return null;
        }
    }
   public String ChangeIdToName(Chapter chapter){
        Chapter dbChapter;
        if(chapter.getId()!=0){
            dbChapter = chapterMapper.findById(chapter.getId());
            if(dbChapter==null){
                return null;
            }else {
                return dbChapter.getName();
            }
        }else {
            return null;
        }
    }


}
