package com.im.mall.controller;

import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller  // 告诉spring 这是一个controller
public class UserController {
    @Autowired // 配合@Service注解就可以找到啦
    UserService userService;
    // 返回对象的基本信息，从service层返回
    @GetMapping("/test")
    @ResponseBody  // 返回json格式
    public User personalPage() {
        return userService.getUser();
    }
}
