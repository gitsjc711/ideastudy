package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.util.StatusUtil;
import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UrlUtil urlUtil;
    @RequestMapping("/findResource")
    @ResponseBody
    public List<ResourceVO> findResource(@RequestBody Chapter chapter) {
        List<Resource> lists= resourceService.findByChapterId(chapter);
        return resourceService.changeToVO(lists);
    }
    @RequestMapping("/findResourceByCourse")
    @ResponseBody
    public List<ResourceVO> findResourceByCourse(@RequestBody Course course) {
        List<Resource> lists= resourceService.findByCourseId(course);
        return resourceService.changeToVO(lists);
    }
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(ResourceDTO resourceDTO) {
        if(resourceDTO.getUrl()==null||resourceDTO.getCourseId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Resource resource = new Resource();
        resource.setChapterId(resourceDTO.getChapterId());
        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        resource.setUrl(resourceDTO.getUrl());
        return resourceService.insert(resource);
    }

}
