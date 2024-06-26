package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.NoticeMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.Notice;
import com.study.idea.demos.web.servie.NoticeService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class NoticeServiceImpl implements NoticeService{
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Notice> findByCourseId(Course course) {
        if(course.getId()==0){
            return null;
        }
        List<Notice> lists=noticeMapper.findByCourseId(course.getId());
        Iterator<Notice> iterator=lists.iterator();
        while(iterator.hasNext()){
            Notice i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Notice notice) {
        if(notice.getContent()==null||notice.getTitle()==null||notice.getTeacherId()==0||notice.getCourseId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(courseMapper.findById(notice.getCourseId())==null||userMapper.findById(notice.getTeacherId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        Date date = new Date();
        notice.setCreateTime(date);
        notice.setUpdateTime(date);
        notice.setStatus(0);
        if(noticeMapper.insert(notice)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
}
