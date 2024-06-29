package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.Notice;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.NoticeVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface NoticeService {
    List<Notice> findByUserId(User user);
    List<Notice> findByCourseId(Course course);
    StatusUtil.ErrorCode insert(Notice notice);
    List<NoticeVO> changeToVO(List<Notice> notices);

}
