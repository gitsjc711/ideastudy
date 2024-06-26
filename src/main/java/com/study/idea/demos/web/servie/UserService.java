package com.study.idea.demos.web.servie;

import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.util.StatusUtil;

public interface UserService {
    StatusUtil.ErrorCode checkLogin(User user);
    StatusUtil.ErrorCode register(User user);
    StatusUtil.ErrorCode update(User user);
    User showDetails(User user);

}
