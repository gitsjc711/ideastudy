package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.HomeworkStudent;
import com.study.idea.demos.web.entity.VO.HomeworkStudentVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface HomeworkService {
    StatusUtil.ErrorCode checkParams(Homework homework);
    List<Homework> findByClassId(Homework homework);
    List<HomeworkStudent> findByHomeworkId(Homework homework);
    StatusUtil.ErrorCode insert(Homework homework);
    StatusUtil.ErrorCode teacherDelete(Homework homework);
    StatusUtil.ErrorCode checkParams(HomeworkStudent homeworkStudent);
    boolean findFinishStatus(HomeworkStudent homeworkStudent);
    StatusUtil.ErrorCode updateFinishHomework(HomeworkStudent homeworkStudent);
    StatusUtil.ErrorCode insert(HomeworkStudent homeworkStudent);
    List<HomeworkStudentVO> changeToVO(List<HomeworkStudent> list);
}
