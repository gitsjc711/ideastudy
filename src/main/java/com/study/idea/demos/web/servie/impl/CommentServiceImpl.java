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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            commentVO.setUpdateTime(i.getUpdateTime());
            commentVO.setContent(i.getContent());
            commentVO.setReplyId(i.getReplyId());
            User user=new User();
            user.setId(i.getUserId());
            commentVO.setUsername(nameUtil.ChangeIdToName(user));
            result.add(commentVO);
        }
        return result;
    }

}
