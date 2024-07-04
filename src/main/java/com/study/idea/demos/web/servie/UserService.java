package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.DTO.CourseDTO;
import com.study.idea.demos.web.entity.DTO.UserDTO;
import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.entity.VO.UserVO;
import com.study.idea.demos.web.util.StatusUtil;

public interface UserService {
    StatusUtil.ErrorCode checkLogin(User user);
    StatusUtil.ErrorCode checkPram(User user);
    StatusUtil.ErrorCode register(User user);
    StatusUtil.ErrorCode update(User user,String code);
    User showDetails(User user);
    User changeToEntity(UserDTO userDTO);
    UserVO changeToVO(User user);
}
