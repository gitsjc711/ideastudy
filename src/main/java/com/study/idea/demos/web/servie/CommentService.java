package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.util.StatusUtil;

import java.util.List;

public interface CommentService {
    List<Comment> findByReplyId(Comment comment);
    StatusUtil.ErrorCode insert(Comment comment);
}
