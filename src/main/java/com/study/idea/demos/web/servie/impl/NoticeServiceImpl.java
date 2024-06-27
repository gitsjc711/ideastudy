package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.NoticeMapper;
import com.study.idea.demos.web.dao.OrderMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.*;
import com.study.idea.demos.web.entity.VO.NoticeVO;
import com.study.idea.demos.web.servie.NoticeService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private NameUtil nameUtil;
    @Override
    public List<Notice> findByUserId(User user) {
        if(user.getId()==0){
            return null;
        }
        List<Order> orderList=orderMapper.findByUserId(user.getId());
        List<Notice> resultList=new ArrayList<>();
        for(Order order:orderList){
            List<Notice> notices=noticeMapper.findByCourseId(order.getCourseId());
            for(Notice notice:notices) {
                if(notice.getStatus()==0){
                    resultList.add(notice);
                }
            }
        }
        return resultList;
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
    public List<NoticeVO> changeToVO(List<Notice> notices){
        List<NoticeVO> resultList=new ArrayList<>();
        if(notices.isEmpty()){
            return resultList;
        }
        for(Notice notice:notices){
            NoticeVO noticeVO=new NoticeVO();
            noticeVO.setId(notice.getId());
            noticeVO.setTitle(notice.getTitle());
            noticeVO.setStatus(notice.getStatus());
            noticeVO.setContent(notice.getContent());
            Course course=new Course();
            course.setId(notice.getCourseId());
            noticeVO.setCourse(nameUtil.ChangeIdToName(course));
            User user=new User();
            user.setId(notice.getTeacherId());
            noticeVO.setTeacher(nameUtil.ChangeIdToName(user));
            resultList.add(noticeVO);
        }
        return resultList;
    }
}
