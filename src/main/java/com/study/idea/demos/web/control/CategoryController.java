package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.servie.CategoryService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Category category) {
        return categoryService.insert(category);
    }
    @GetMapping("/all")
    @ResponseBody
    public List<Category> findAll(){
        return categoryService.findAll();
    }

}
