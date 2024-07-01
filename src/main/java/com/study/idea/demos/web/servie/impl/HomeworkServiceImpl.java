package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.HomeworkMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.HomeworkStudent;
import com.study.idea.demos.web.servie.HomeworkService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class HomeworkServiceImpl implements HomeworkService {
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public StatusUtil.ErrorCode checkParams(Homework homework){
        if(homework.getCourseId()==0||homework.getChapterOrder()==0||homework.getName()==null||homework.getDescription()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(courseMapper.findById(homework.getCourseId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        List<Chapter> dbChapter=chapterMapper.findByCourseId(homework.getCourseId());
        boolean flag=false;
        for(Chapter it:dbChapter){
            if(it.getChapterOrder()==homework.getChapterOrder()){
                flag=true;
            }
        }
        if(!flag){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        return StatusUtil.ErrorCode.OK;
    }
    @Override
    public StatusUtil.ErrorCode checkParams(HomeworkStudent homeworkStudent){
        if(homeworkStudent.getHomeworkId()==0||homeworkStudent.getUserId()==0||homeworkStudent.getHomeworkUrl()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(homeworkMapper.findById(homeworkStudent.getHomeworkId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if(userMapper.findById(homeworkStudent.getUserId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        return StatusUtil.ErrorCode.OK;
    }
    @Override
    public List<Homework> findByClassId(Homework homework) {
        if(homework.getCourseId()==0){
            return null;
        }
        List<Homework> lists=homeworkMapper.findByCourseId(homework.getCourseId());
        Iterator<Homework> iterator=lists.iterator();
        while(iterator.hasNext()){
            Homework i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }
    @Override
    public boolean findFinishStatus(HomeworkStudent homeworkStudent){
        List<HomeworkStudent> lists=homeworkMapper.findByHomeworkId(homeworkStudent.getHomeworkId());
        if(lists==null){
            return false;
        }
        for(HomeworkStudent i:lists){
            if(i.getUserId()==homeworkStudent.getUserId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public StatusUtil.ErrorCode insert(Homework homework) {
        Date date = new Date();
        homework.setCreateTime(date);
        homework.setUpdateTime(date);
        homework.setStatus(0);
        if(homeworkMapper.insert(homework)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode insert(HomeworkStudent homeworkStudent){
        Date date = new Date();
        homeworkStudent.setCreateTime(date);
        homeworkStudent.setUpdateTime(date);
        homeworkStudent.setStatus(0);
        if(homeworkMapper.insertFinishHomework(homeworkStudent)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
}
