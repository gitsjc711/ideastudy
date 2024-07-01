package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.ChapterMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.ResourceMapper;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ChapterVO;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ChapterService;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private UrlUtil urlUtil;
    @Override
    public StatusUtil.ErrorCode checkParams(Chapter chapter){
        if(chapter.getChapterOrder()==0||chapter.getName()==null||chapter.getCourseId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if(courseMapper.findById(chapter.getCourseId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        List<Chapter> chapters= chapterMapper.findByCourseId(chapter.getCourseId());
        if(chapters!=null) {
            for (Chapter it : chapters) {
                if (it.getChapterOrder() == chapter.getChapterOrder()) {
                    return StatusUtil.ErrorCode.ALREADY_EXISTS;
                }
            }
        }
        return StatusUtil.ErrorCode.OK;
    }
    @Override
    public List<Chapter> findByCourseId(Course course) {
        if(course.getId()== 0){
            return null;
        }
        List<Chapter> lists=chapterMapper.findByCourseId(course.getId());
        Iterator<Chapter> iterator=lists.iterator();
        while(iterator.hasNext()){
            Chapter i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Chapter chapter) {
        Date date = new Date();
        chapter.setCreateTime(date);
        chapter.setUpdateTime(date);
        chapter.setStatus(0);
        if(chapterMapper.insert(chapter)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public StatusUtil.ErrorCode update(Chapter chapter) {
        if (chapter.getId() == 0) {
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if (chapterMapper.findById(chapter.getId()) == null) {
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if (chapterMapper.update(chapter.getId())) {
            return StatusUtil.ErrorCode.OK;
        } else {
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
    @Override
    public List<ChapterVO> changeToVO(List<Chapter> chapters){
        if(chapters==null) {
            return null;
        }
        List<ChapterVO> list=new ArrayList<ChapterVO>();
        for(Chapter i:chapters){
            if(i.getStatus()!=1){
                ChapterVO chapterVO=new ChapterVO();
                chapterVO.setId(i.getId());
                chapterVO.setLabel(i.getName());
                chapterVO.setChapterOrder(i.getChapterOrder());
                List<Resource> resources=resourceMapper.findByChapterId(i.getId());
                if(resources!=null){
                    List<ResourceVO> resourceVOS=new ArrayList<ResourceVO>();
                    for(Resource j:resources){
                        if(j.getStatus()!=1){
                            ResourceVO resourceVO=new ResourceVO();
                            resourceVO.setId(j.getId());
                            resourceVO.setLabel(j.getName());
                            resourceVO.setType(j.getType());
                            resourceVO.setUrl(urlUtil.changeToRequestUrl(j.getUrl()));
                            resourceVOS.add(resourceVO);
                        }
                    }
                    chapterVO.setChildren(resourceVOS);
                }else{
                    chapterVO.setChildren(null);
                }
                list.add(chapterVO);
            }
        }
        return list;
    }
}
