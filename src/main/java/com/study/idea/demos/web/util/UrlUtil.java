package com.study.idea.demos.web.util;

import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UrlUtil {
    @Autowired
    private NameUtil nameUtil;
    private static String videoPath = "G:\\resource\\video";
    private static String imagePath = "G:\\resource\\image";
    public  String getUrl(ResourceDTO resourceDTO) {
        String url="";
        Course course=new Course();
        course.setId(resourceDTO.getCourseId());
        Chapter chapter=new Chapter();
        chapter.setId(resourceDTO.getChapterId());
        switch (resourceDTO.getType()){
            case "video":
                url=videoPath+"\\"+nameUtil.ChangeIdToName(course)+"\\"+nameUtil.ChangeIdToName(chapter)+"\\"+resourceDTO.getName();
                break;
            case "image":
                url=imagePath+"\\"+nameUtil.ChangeIdToName(course)+"\\"+nameUtil.ChangeIdToName(chapter)+"\\"+resourceDTO.getName();
                break;
        }
        return url;
    }
}
