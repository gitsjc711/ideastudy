package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.entity.VO.CommentVO;
import com.study.idea.demos.web.servie.CommentService;
import com.study.idea.demos.web.util.StatusUtil;
import com.sun.org.apache.regexp.internal.RE;
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
        StatusUtil.ErrorCode errorCode=commentService.checkPram(comment);
        if(errorCode!= StatusUtil.ErrorCode.OK){
            return errorCode;
        }
        return commentService.insert(comment);
    }
    @RequestMapping("/findByReplyId")
    @ResponseBody
    public List<CommentVO> findByReplyId(@RequestBody Comment comment)
    {

        List<Comment> list=commentService.findByReplyId(comment);
        return commentService.changeToVO(list);
    }
    @RequestMapping("/findByCourseId")
    @ResponseBody
    public List<CommentVO> findByCourseId(@RequestBody Comment comment){
        List<Comment> list= commentService.findByCourseId(comment);
        return commentService.changeToVO(list);
    }
}
