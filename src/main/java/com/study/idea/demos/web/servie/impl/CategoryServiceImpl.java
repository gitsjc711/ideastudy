package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CategoryMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CategoryDTO;
import com.study.idea.demos.web.servie.CategoryService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Category> findAll() {
        List<Category> lists=categoryMapper.findAll();
        Iterator<Category> iterator=lists.iterator();
        while(iterator.hasNext()){
            Category i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }
    @Override
    public StatusUtil.ErrorCode insert(Category category,String role) {
        if(!role.equals("管理员")){
            return StatusUtil.ErrorCode.NO_PERMISSION;
        }
        if(category.getCategoryName()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(categoryMapper.findByName(category)!=null){
            return StatusUtil.ErrorCode.ALREADY_EXISTS;
        }
        Date date = new Date();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        category.setStatus(0);
        category.setParentId(0);
        if(categoryMapper.insert(category)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode delete(Category category,String role){
        if(category.getId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(!role.equals("管理员")){
            return StatusUtil.ErrorCode.NO_PERMISSION;
        }
        List<Course> courses = courseMapper.findByCategoryId(category.getId());
        if(!courses.isEmpty()){
            return StatusUtil.ErrorCode.TOO_MANY_CONTAINS;
        }
        if(categoryMapper.delete(category.getId())){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }

    }

    @Override
    public Category changeToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setParentId(0);
        return category;
    }

}
