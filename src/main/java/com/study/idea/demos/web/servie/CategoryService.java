package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    StatusUtil.ErrorCode insert(Category category);
}
