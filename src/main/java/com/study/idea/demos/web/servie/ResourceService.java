package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Resource;
import com.study.idea.demos.web.entity.VO.ResourceVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface ResourceService {
    List<Resource> findByChapterId(Chapter chapter);
    StatusUtil.ErrorCode insert(Resource resource);
    StatusUtil.ErrorCode checkPram(Resource resource,int courseId,String url);
    List<ResourceVO> changeToVO(List<Resource> resources);

}
