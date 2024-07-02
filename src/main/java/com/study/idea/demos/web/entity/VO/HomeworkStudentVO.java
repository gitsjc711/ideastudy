package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.Date;

@Data
public class HomeworkStudentVO {
    int id;
    Date updateTime;
    String username;
    String homeworkName;
    String homeworkUrl;
    String homeworkType;
}
