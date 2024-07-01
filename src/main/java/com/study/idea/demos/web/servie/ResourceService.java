package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface ResourceService {
    List<Resource> findByChapterId(Chapter chapter);
    List<Resource> findByCourseId(Course course);
    StatusUtil.ErrorCode insert(Resource resource);
    StatusUtil.ErrorCode checkPram(ResourceDTO resourceDTO);
    List<ResourceVO> changeToVO(List<Resource> resources);
    Resource changeToEntity(ResourceDTO resourceDTO);

}
