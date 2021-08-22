package com.im.mall.controller;

import com.im.mall.common.ApiRestResponse;
import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 用户控制器
 */
@Controller  // 告诉spring 这是一个controller
public class UserController {
    @Autowired // 配合@Service注解就可以找到啦  -- 实体类上加的
    UserService userService;
    // 返回对象的基本信息，从service层返回
    @GetMapping("/test")
    @ResponseBody  // 返回json格式
    public User personalPage() {
        return userService.getUser();
    }

    @PostMapping("/register")
    @ResponseBody // 作为Json 返回
    public ApiRestResponse register(@RequestParam("userName") String userName, @RequestParam("password") String password) throws MallException {
        // 1. 为空校验
        if(ObjectUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }

        if(ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        // 密码长度不能少于8位
        if(password.length() < 8) {
            return ApiRestResponse.error(MallExceptionEnum.PASSWORD_TOO_SHORT);
        }

        // 通过
        userService.register(userName, password);

        return ApiRestResponse.success();

    }
}
