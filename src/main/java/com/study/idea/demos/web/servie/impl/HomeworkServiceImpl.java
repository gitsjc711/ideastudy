package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.HomeworkMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Homework;
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
    @Override
    public List<Homework> findByClassIdAndChapterOrder(Homework homework) {
        if(homework.getChapterOrder()==0||homework.getCourseId()==0){
            return null;
        }
        List<Homework> lists=homeworkMapper.findByClassIdAndChapterOrder(homework.getCourseId(),homework.getChapterOrder());
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
    public StatusUtil.ErrorCode insert(Homework homework) {
        if(homework.getCourseId()==0||homework.getChapterOrder()==0||homework.getName()==null||homework.getDescription()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
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
}
