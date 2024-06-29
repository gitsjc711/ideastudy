package com.study.idea.demos.web.entity.VO;

import lombok.Data;

import java.util.List;

@Data
public class ChapterVO {
    private int id;
    private int chapterOrder;
    private String label;
    private List<ResourceVO> children;
}
