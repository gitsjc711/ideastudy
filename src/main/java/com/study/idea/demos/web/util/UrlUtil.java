package com.study.idea.demos.web.util;

import com.study.idea.demos.web.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UrlUtil {
    @Autowired
    private NameUtil nameUtil;
    private static String videoPath = "G:\\resource\\video";
    private static String imagePath = "G:\\resource\\image";
    private static String filePath = "G:\\resource\\file";

    public String getUrl(MultipartFile file){
        String url="";
        if(file.getContentType().equals("image/png")||file.getContentType().equals("image/jpeg")||file.getContentType().equals("image/gif")||file.getContentType().equals("image/jpg")){
            url=imagePath;
        }else if(file.getContentType().equals("video/mp4")){
            url=videoPath;
        }else{
            url=filePath;
        }
        return url;
    }

    public String changeToRequestUrl(String url){
        String requestUrl=url.replace("G:\\resource\\", WebConfig.basePath).replace("\\","/");

        return requestUrl;
    }
}
