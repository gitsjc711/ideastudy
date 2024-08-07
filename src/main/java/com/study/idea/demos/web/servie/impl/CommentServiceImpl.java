package com.study.idea.demos.web.servie.impl;

import com.study.idea.demos.web.dao.CommentMapper;
import com.study.idea.demos.web.dao.CourseMapper;
import com.study.idea.demos.web.dao.UserMapper;
import com.study.idea.demos.web.entity.Comment;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.CommentVO;
import com.study.idea.demos.web.servie.CommentService;
import com.study.idea.demos.web.util.NameUtil;
import com.study.idea.demos.web.util.StatusUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NameUtil nameUtil;
    @Override
    public StatusUtil.ErrorCode checkPram(Comment comment){
        if(comment.getContent()==null||comment.getCourseId()==0||comment.getUserId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        if((comment.getReplyId()!=0&&commentMapper.findById(comment.getReplyId())==null)||courseMapper.findById(comment.getCourseId())==null||userMapper.findById(comment.getUserId())==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        return StatusUtil.ErrorCode.OK;
    }

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
    public List<Comment> findByCourseId(Comment comment){
        if(comment.getCourseId()==0){
            return null;
        }
        if(courseMapper.findById(comment.getCourseId())==null){
            return null;
        }
        List<Comment> lists=commentMapper.findByCourseId(comment.getCourseId());
        Iterator<Comment> iterator=lists.iterator();
        while(iterator.hasNext()){
            Comment i = iterator.next();
            if(i.getStatus() == 1){
                iterator.remove();
            }
            if(i.getReplyId()!=0){
                iterator.remove();
            }
        }
        return lists;
    }

    @Override
    public StatusUtil.ErrorCode insert(Comment comment) {
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
    @Override
    public List<CommentVO> changeToVO(List<Comment> commentList){
        if(commentList==null){
            return null;
        }
        List<CommentVO> result=new ArrayList<>();
        for(Comment i:commentList){
            CommentVO commentVO=new CommentVO();
            commentVO.setId(i.getId());
            commentVO.setUpdateTime(i.getUpdateTime().toString());
            commentVO.setContent(i.getContent());
            commentVO.setReplyId(i.getReplyId());
            User user=new User();
            user.setId(i.getUserId());
            commentVO.setUsername(nameUtil.ChangeIdToNickname(user));
            result.add(commentVO);
        }
        return result;
    }
    @Override
    public StatusUtil.ErrorCode delete(Comment comment){
        if(comment.getId()==0||comment.getUserId()==0){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Comment dbComment=commentMapper.findById(comment.getId());
        if(dbComment==null){
            return StatusUtil.ErrorCode.NOT_EXISTS;
        }
        if(dbComment.getUserId()!=comment.getUserId()){
            return StatusUtil.ErrorCode.NO_PERMISSION;
        }
        List<Comment> replyList=commentMapper.findByReplyId(comment.getId());
        if(replyList!=null){
            for(Comment i:replyList){
                commentMapper.delete(i.getId());
            }
        }
        if(commentMapper.delete(comment.getId())){
            return StatusUtil.ErrorCode.OK;
        }else{
            return StatusUtil.ErrorCode.UNKNOWN_ERROR;
        }
    }

}
