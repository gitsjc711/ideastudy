package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.entity.VO.CommentVO;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CommentService {
    StatusUtil.ErrorCode checkPram(Comment comment);
    List<Comment> findByReplyId(Comment comment);
    List<Comment> findByCourseId(Comment comment);
    StatusUtil.ErrorCode insert(Comment comment);
    List<CommentVO> changeToVO(List<Comment> commentList);
}
