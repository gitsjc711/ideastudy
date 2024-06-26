package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.config.WebConfig;
import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.ResourceMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Override
    public List<Resource> findByChapterId(Chapter chapter) {
        if(chapter.getId()==0){
            return null;
        }
        List<Resource> lists = resourceMapper.findByChapterId(chapter.getId());
        Iterator<Resource> iterator=lists.iterator();
        while(iterator.hasNext()){
            Resource i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Resource resource) {
        Date date = new Date();
        resource.setCreateTime(date);
        resource.setUpdateTime(date);
        resource.setStatus(0);
        if (resourceMapper.insert(resource)) {
            return StatusUtil.ErrorCode.OK;
        } else {
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode checkPram(Resource resource,int courseId,String url){
        if (resource.getChapterId() == 0 || resource.getType() == null || resource.getName() == null) {
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Resource dbResource= resourceMapper.findByUrl(url);
        if(dbResource!=null){
            return StatusUtil.ErrorCode.ALREADY_EXISTS;
        }
        Chapter chapter= chapterMapper.findById(resource.getChapterId());
        if(chapter==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        Course course=courseMapper.findById(courseId);
        if(course==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if(chapter.getCourseId()!=courseId){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }
    public List<ResourceVO> changeToVO(List<Resource> resources){
        List<ResourceVO> result=new ArrayList<>();
        for(Resource resource:resources){
            ResourceVO resourceVO=new ResourceVO();
            resourceVO.setId(resource.getId());
            resourceVO.setStatus(resource.getStatus());
            resourceVO.setName(resource.getName());
            resourceVO.setType(resource.getType());
            resourceVO.setUrl(resource.getUrl());
            Chapter chapter=chapterMapper.findById(resource.getChapterId());
            Course course=courseMapper.findById(chapter.getCourseId());
            resourceVO.setChapterName(chapter.getName());
            resourceVO.setCourseName(course.getName());
            resourceVO.setRequestUrl(changeToRequestUrl(resource.getUrl()));
            result.add(resourceVO);
        }
        return result;
    }

    public String changeToRequestUrl(String url){
        String requestUrl=url.replace("G:\\resource\\", WebConfig.basePath).replace("\\","/");

        return requestUrl;
    }

}