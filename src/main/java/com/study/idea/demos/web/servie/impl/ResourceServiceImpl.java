package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.config.WebConfig;
import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.ResourceMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import com.sun.org.apache.regexp.internal.RE;
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
    @Autowired
    private UrlUtil urlUtil;
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
    public List<Resource> findByCourseId(Course course){
        if(course.getId()==0){
            return null;
        }
        List<Chapter> list = chapterMapper.findByCourseId(course.getId());
        if(list==null){
            return null;
        }
        List<Resource> lists = new ArrayList<>();
        for(Chapter i:list){
            List<Resource> list1 = resourceMapper.findByChapterId(i.getId());
            lists.addAll(list1);
        }
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
    public StatusUtil.ErrorCode checkPram(ResourceDTO resourceDTO){
        if (resourceDTO.getChapterOrder() == 0 || resourceDTO.getName() == null||resourceDTO.getUrl()==null||resourceDTO.getCourseId()==0) {
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Resource resource = new Resource();
        Course course = new Course();
        course.setId(resourceDTO.getCourseId());
        Course dbCourse=courseMapper.findById(course.getId());
        if(dbCourse==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        List<Chapter> chapterList=chapterMapper.findByCourseId(course.getId());
        if(chapterList==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        for(Chapter i:chapterList){
            if(i.getChapterOrder()==resourceDTO.getChapterOrder()){
                resource.setChapterId(i.getId());
                break;
            }
        }
        if(resource.getChapterId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }
    public List<ResourceVO> changeToVO(List<Resource> resources){
        if(resources==null){
            return null;
        }
        List<ResourceVO> result=new ArrayList<>();
        for(Resource i:resources){
            ResourceVO resourceVO=new ResourceVO();
            resourceVO.setId(i.getId());
            resourceVO.setLabel(i.getName());
            resourceVO.setType(i.getType());
            resourceVO.setUrl(urlUtil.changeToRequestUrl(i.getUrl()));
            result.add(resourceVO);
        }
        return result;
    }
    public Resource changeToEntity(ResourceDTO resourceDTO){
        if(resourceDTO==null){
            return null;
        }
        Course course=new Course();
        course.setId(resourceDTO.getCourseId());

        List<Chapter> chapterList=chapterMapper.findByCourseId(course.getId());
        Resource resource=new Resource();
        for(Chapter j:chapterList){
            if(j.getChapterOrder()==resourceDTO.getChapterOrder()){
                resource.setChapterId(j.getId());
                break;
            }
        }
        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        resource.setUrl(resourceDTO.getUrl());
        return resource;
    }
}
