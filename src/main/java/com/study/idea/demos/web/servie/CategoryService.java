package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.DTO.CategoryDTO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    StatusUtil.ErrorCode insert(Category category,String role);
    StatusUtil.ErrorCode delete(Category category,String role);
    Category changeToEntity(CategoryDTO categoryDTO);
}
