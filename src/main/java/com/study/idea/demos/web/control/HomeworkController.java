package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.servie.HomeworkService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Homework homework)
    {
        return homeworkService.insert(homework);
    }
    @RequestMapping("/findHomework")
    @ResponseBody
    public List<Homework> findHomework(@RequestBody Homework homework)
    {
        return homeworkService.findByClassIdAndChapterOrder(homework);
    }
}
