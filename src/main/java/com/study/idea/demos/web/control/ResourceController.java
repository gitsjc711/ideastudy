package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Chapter;
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
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(ResourceDTO resourceDTO) {
        if(resourceDTO.getFile()==null||resourceDTO.getCourseId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Resource resource = new Resource();
        resource.setChapterId(resourceDTO.getChapterId());
        resource.setName(resourceDTO.getName());
        resource.setType(resourceDTO.getType());
        String url = urlUtil.getUrl(resourceDTO);
        String writeUrl=url+"\\"+resourceDTO.getFile().getOriginalFilename();
        StatusUtil.ErrorCode code=resourceService.checkPram(resource,resourceDTO.getCourseId(),writeUrl);
        if(code!=StatusUtil.ErrorCode.OK){
            return code;
        }
        File dir=new File(url);
        if(!dir.exists()){
            dir.mkdirs();
        }
        resource.setUrl(writeUrl);
        File dest=new File(writeUrl);
        try {
            resourceDTO.getFile().transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resourceService.insert(resource);
    }

}
