package com.study.idea;

import com.study.idea.demos.web.entity.User;
import com.study.idea.demos.web.servie.ResourceService;
import com.study.idea.demos.web.servie.impl.ResourceServiceImpl;
import com.study.idea.demos.web.servie.impl.UserServiceImpl;
import com.study.idea.demos.web.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdeastudyApplicationTests {
    @Autowired
    ResourceServiceImpl resourceService;
    @Autowired
    UserServiceImpl userService;
    @Test
    void testUtil() {
        String salt=MD5Util.generateSalt("123456");
        String str=MD5Util.inputPassToDBPass("123456",salt);
        System.out.println(str);

    }
    @Test
    void testUserService(){
        User user=new User();
        user.setAccount("123456");
        user.setPassword("123456");
        user.setEmail("1090737321@qq.com");
        user.setRole(0);
        System.out.println(userService.register(user));
    }
    @Test
    void testCheckLogin(){
        User user=new User();
        user.setAccount("123456");
        user.setPassword("123456");
        System.out.println(userService.checkLogin(user));
    }
    @Test
    void testChangeRequestUrl(){
        String url="G:\\resource\\image\\zhanghao\\排序算法\\深入学习CSAPP\\wallhaven-1kgllv.jpg";

        System.out.println(resourceService.changeToRequestUrl(url));
    }

}
