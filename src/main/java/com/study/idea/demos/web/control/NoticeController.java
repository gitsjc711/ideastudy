package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Notice;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.NoticeVO;
import com.study.idea.demos.web.servie.NoticeService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Notice notice) {
        StatusUtil.ErrorCode errorCode=noticeService.checkParams(notice);
        if(errorCode!=StatusUtil.ErrorCode.OK) {
            return errorCode;
        }
        return noticeService.insert(notice);
    }
    @RequestMapping("/findNotice")
    @ResponseBody
    public List<NoticeVO> findNotice(@RequestBody User user) {
        List<Notice> list=noticeService.findByUserId(user);
        return noticeService.changeToVO(list);
    }
    @RequestMapping("/findNoticeByCourse")
    @ResponseBody
    public List<NoticeVO> findNoticeByCourse(@RequestBody Course course) {
        List<Notice> list=noticeService.findByCourseId(course);
        return noticeService.changeToVO(list);
    }
}
