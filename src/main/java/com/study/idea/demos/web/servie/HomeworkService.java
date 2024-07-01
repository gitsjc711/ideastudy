package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.HomeworkStudent;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface HomeworkService {
    StatusUtil.ErrorCode checkParams(Homework homework);
    StatusUtil.ErrorCode checkParams(HomeworkStudent homeworkStudent);
    List<Homework> findByClassId(Homework homework);
    boolean findFinishStatus(HomeworkStudent homeworkStudent);
    StatusUtil.ErrorCode insert(Homework homework);
    StatusUtil.ErrorCode insert(HomeworkStudent homeworkStudent);

}
