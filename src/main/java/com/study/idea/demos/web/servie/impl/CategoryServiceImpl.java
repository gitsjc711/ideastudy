package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CategoryMapper;
import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Chapter;
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
    public StatusUtil.ErrorCode insert(Category category) {
        if(category.getCategoryLogo()==null||category.getCategoryName()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(categoryMapper.findByName(category)!=null){
            return StatusUtil.ErrorCode.ALREADY_EXISTS;
        }
        Date date = new Date();
        category.setCreateTime(date);
        category.setUpdateTime(date);
        category.setStatus(0);
        if(categoryMapper.insert(category)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }

}
