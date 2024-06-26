package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface HomeworkService {
    List<Homework> findByClassIdAndChapterOrder(Homework homework);
    StatusUtil.ErrorCode insert(Homework homework);

}
