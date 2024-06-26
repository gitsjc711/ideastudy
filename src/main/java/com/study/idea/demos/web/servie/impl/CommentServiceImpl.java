package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CommentMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Category;
import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.servie.CommentService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<Comment> findByReplyId(Comment comment) {
        List<Comment> lists=commentMapper.findByReplyId(comment.getReplyId());
        Iterator<Comment> iterator=lists.iterator();
        while(iterator.hasNext()){
            Comment i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Comment comment) {
        if(comment.getContent()==null||comment.getCourseId()==0||comment.getUserId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if((comment.getReplyId()!=0&&commentMapper.findById(comment.getReplyId())==null)||courseMapper.findById(comment.getCourseId())==null||userMapper.findById(comment.getUserId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        Date date = new Date();
        comment.setCreateTime(date);
        comment.setUpdateTime(date);
        comment.setStatus(0);
        if(commentMapper.insert(comment)){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }
}
