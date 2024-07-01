package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;
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
    public StatusUtil.ErrorCode add(@RequestBody ResourceDTO resourceDTO) throws Exception {
        StatusUtil.ErrorCode errorCode = resourceService.checkPram(resourceDTO);
        if(errorCode!=StatusUtil.ErrorCode.OK){
            return errorCode;
        }
        Path path = new File(resourceDTO.getUrl()).toPath();
        String mimeType = Files.probeContentType(path);
        resourceDTO.setType(mimeType);
        Resource resource = resourceService.changeToEntity(resourceDTO);
        return resourceService.insert(resource);
    }

}
