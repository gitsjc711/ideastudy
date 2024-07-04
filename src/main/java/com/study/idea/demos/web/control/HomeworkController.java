package com.study.idea.demos.web.control;

import com.study.idea.demos.web.entity.Homework;
import com.study.idea.demos.web.entity.HomeworkStudent;
import com.study.idea.demos.web.entity.VO.HomeworkStudentVO;
import com.study.idea.demos.web.servie.HomeworkService;
import com.study.idea.demos.web.util.StatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
    @Autowired
    private HomeworkService homeworkService;
    @RequestMapping("/add")
    @ResponseBody
    public StatusUtil.ErrorCode add(@RequestBody Homework homework)
    {
        StatusUtil.ErrorCode errorCode = homeworkService.checkParams(homework);
            if(errorCode != StatusUtil.ErrorCode.OK)
        {
            return errorCode;
        }
        return homeworkService.insert(homework);
    }
    @RequestMapping("/findHomework")
    @ResponseBody
    public List<Homework> findHomework(@RequestBody Homework homework)
    {
        return homeworkService.findByClassId(homework);
    }
    @RequestMapping("/finish")
    @ResponseBody
    public StatusUtil.ErrorCode finish(@RequestBody HomeworkStudent homeworkStudent) throws Exception
    {
        StatusUtil.ErrorCode errorCode = homeworkService.checkParams(homeworkStudent);
        if(errorCode != StatusUtil.ErrorCode.OK)
        {
            return errorCode;
        }
        Path path = new File(homeworkStudent.getHomeworkUrl()).toPath();
        String mimeType = Files.probeContentType(path);
        homeworkStudent.setHomeworkType(mimeType);
        return homeworkService.insert(homeworkStudent);
    }
    @RequestMapping("/findStatus")
    @ResponseBody
    public boolean findStatus(@RequestBody HomeworkStudent homeworkStudent)
    {
        if(homeworkStudent.getUserId() == 0|| homeworkStudent.getHomeworkId() == 0){
            return false;
        }
        return homeworkService.findFinishStatus(homeworkStudent);
    }
    @RequestMapping("/findFinishHomework")
    @ResponseBody
    public List<HomeworkStudentVO> findFinishHomework(@RequestBody Homework homework)
    {
        List<HomeworkStudent> list = homeworkService.findByHomeworkId(homework);
        return homeworkService.changeToVO(list);
    }
    @RequestMapping("/teacherDelete")
    @ResponseBody
    public StatusUtil.ErrorCode teacherDelete(@RequestBody Homework homework)
    {
        return homeworkService.teacherDelete(homework);
    }
    @RequestMapping("/updateFinishHomework")
    @ResponseBody
    public StatusUtil.ErrorCode updateFinishHomework(@RequestBody HomeworkStudent homeworkStudent)
    {
        if(homeworkStudent.getHomeworkUrl()==null){
            return StatusUtil.ErrorCode.PARAMETER_ERROR;
        }
        Path path = new File(homeworkStudent.getHomeworkUrl()).toPath();
        String mimeType;
        try {
            mimeType = Files.probeContentType(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        homeworkStudent.setHomeworkType(mimeType);
        return homeworkService.updateFinishHomework(homeworkStudent);
    }

}
