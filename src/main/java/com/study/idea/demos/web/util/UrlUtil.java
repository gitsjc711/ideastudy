package com.study.idea.demos.web.util;

import com.study.idea.demos.web.config.WebConfig;
import com.study.idea.demos.web.entity.Chapter;
import com.study.idea.demos.web.entity.Course;
import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.DTO.ResourceDTO;
import com.study.idea.demos.web.entity.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UrlUtil {
    @Autowired
    private NameUtil nameUtil;
    private static String videoPath = "G:\\resource\\video";
    private static String imagePath = "G:\\resource\\image";
    private static String logoPath = "G:\\resource\\logo";
    private static String avatarPath = "G:\\resource\\avatar";
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
    public String getUrl(CourseDTO courseDTO){
        String url="";
        Course course=new Course();
        course.setId(courseDTO.getId());
        url=logoPath+"\\"+nameUtil.ChangeIdToName(course);
        return url;
    }
    public String getUrl(UserDTO userDTO){
        String url="";
        url=avatarPath+"\\"+userDTO.getAccount();
        return url;
    }
    public String getUrl(MultipartFile file){
        String url="";
        if(file.getContentType().equals("image/png")||file.getContentType().equals("image/jpeg")||file.getContentType().equals("image/gif")||file.getContentType().equals("image/jpg")){
            url=imagePath;
        }else{
            url=videoPath;
        }
        return url;
    }

    public String changeToRequestUrl(String url){
        String requestUrl=url.replace("G:\\resource\\", WebConfig.basePath).replace("\\","/");

        return requestUrl;
    }
}
