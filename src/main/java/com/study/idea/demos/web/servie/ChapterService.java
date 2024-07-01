package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.VO.ChapterVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface ChapterService {
    StatusUtil.ErrorCode checkParams(Chapter chapter);
    List<Chapter> findByCourseId(Course course);
    StatusUtil.ErrorCode insert(Chapter chapter);
    StatusUtil.ErrorCode update(Chapter chapter);
    List<ChapterVO> changeToVO(List<Chapter> chapters);
}
