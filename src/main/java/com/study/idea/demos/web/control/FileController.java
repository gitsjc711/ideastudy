package com.study.idea.demos.web.control;

import com.study.idea.demos.web.util.UrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("/file")
@Controller
public class FileController {
    @Autowired
    private UrlUtil urlUtil;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
        String dirUrl=urlUtil.getUrl(file);
        String writeUrl=dirUrl+"\\"+file.getOriginalFilename();
        File dir=new File(dirUrl);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File dest=new File(writeUrl);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return writeUrl;
    }
}
