package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.servie.CommentService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Comment comment)
    {
        return commentService.insert(comment);
    }
    @RequestMapping("/findByReplyId")
    @ResponseBody
    public List<Comment> findByReplyId(Comment comment)
    {
        return commentService.findByReplyId(comment);
    }
}
