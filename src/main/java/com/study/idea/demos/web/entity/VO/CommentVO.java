package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    int id;
    Date updateTime;
    String username;
    String content;
    int replyId;
}
