package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.VO.ChapterVO;
import com.study.idea.demos.web.servie.ChapterService;
import com.study.idea.demos.web.util.StatusUtil;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Chapter chapter) {
        return chapterService.insert(chapter);
    }
    @RequestMapping("/findChapter")
    @ResponseBody
    public List<ChapterVO> findChapter(@RequestBody Course course) {
        List<Chapter> list=chapterService.findByCourseId(course);
        return chapterService.changeToVO(list);
    }
    @RequestMapping("/update")
    @ResponseBody
    public StatusUtil.ErrorCode update(@RequestBody Chapter chapter) {
        return chapterService.update(chapter);
    }
}
