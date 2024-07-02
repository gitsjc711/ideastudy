package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.ResourceMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.Progress;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @Autowired
    private UserMapper userMapper;
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
    public StatusUtil.ErrorCode delete(Resource resource){
        if(resource.getId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Resource dbResource = resourceMapper.findById(resource.getId());
        if(dbResource==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        Path path=  Paths.get(dbResource.getUrl());
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if(resourceMapper.delete(resource.getId())){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }

    @Override
    public StatusUtil.ErrorCode insert(Progress progress){
        if(progress.getUserId()==0||progress.getResourceId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(resourceMapper.findByUserIdAndResourceId(progress)!=null){
            return StatusUtil.ErrorCode.ALREADY_EXISTS;
        }
        Date date = new Date();
        progress.setCreateTime(date);
        progress.setUpdateTime(date);
        progress.setStatus(0);
        if(resourceMapper.insertProgress(progress)){
            return StatusUtil.ErrorCode.OK;
        }else{
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
    @Override
    public StatusUtil.ErrorCode checkPram(Progress progress){
        if(progress.getUserId()==0||progress.getResourceId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        return StatusUtil.ErrorCode.OK;
    }
    public List<ResourceVO> changeToVO(List<Resource> resources,int uid){
        if(resources==null||userMapper.findById(uid)==null){
            return null;
        }
        Progress progress=new Progress();
        progress.setUserId(uid);
        List<ResourceVO> result=new ArrayList<>();
        for(Resource i:resources){
            ResourceVO resourceVO=new ResourceVO();
            progress.setResourceId(i.getId());
            Progress dbProgress=resourceMapper.findByUserIdAndResourceId(progress);
            if(dbProgress!=null){
                resourceVO.setLearned("已学习");
            }else {
                resourceVO.setLearned("");
            }
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
