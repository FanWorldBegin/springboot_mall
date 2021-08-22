package com.im.mall.controller;

import com.im.mall.common.ApiRestResponse;
import com.im.mall.common.Constant;
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

import javax.servlet.http.HttpSession;

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

    @PostMapping("/login")
    @ResponseBody // 作为Json 返回
    public ApiRestResponse login(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password,
                                 HttpSession session) throws MallException {
        // 1. 为空校验
        if(ObjectUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }

        if(ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(userName, password);

        // 不需要返回password - 增加安全性
        user.setPassword(null);
        // user对象放入session
        session.setAttribute(Constant.MALL_USER, user);
        return ApiRestResponse.success(user);
    }

    // 需要获取用户信息 所以需要session
    @PostMapping("/user/update")
    @ResponseBody // 作为Json 返回
    public  ApiRestResponse updateUserInfo(HttpSession session, @RequestParam String signature) throws MallException {
        // 从session 中拿到user对象 - 已知是user类型
        // currentUser 当前已登录的用户
        User currentUser = (User)session.getAttribute(Constant.MALL_USER);
        // 从session 中没有提示登录
        if(currentUser == null) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_LOGIN);
        }


        User user = new User();
        // 主键 - 更新当前用户的签名
        user.setId(currentUser.getId());
        user.setPersonalizedSignature(signature);

        userService.updateInformation(user);
        return ApiRestResponse.success();

    }

    /**
     * 登出 - 清楚session
     * @param session
     * @return
     */
    @PostMapping("/user/logout")
    @ResponseBody // 作为Json 返回
    public  ApiRestResponse logout(HttpSession session) {
        // 删除key MALL_USER 所对应的值
        session.removeAttribute(Constant.MALL_USER);
        return ApiRestResponse.success();
    }


    /**
     * 管理员登录
     * @param userName
     * @param password
     * @param session
     * @return
     * @throws MallException
     */
    @PostMapping("/adminLogin")
    @ResponseBody // 作为Json 返回
    public ApiRestResponse adminLogin(@RequestParam("userName") String userName,
                                 @RequestParam("password") String password,
                                 HttpSession session) throws MallException {
        // 1. 为空校验
        if(ObjectUtils.isEmpty(userName)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_USER_NAME);
        }

        if(ObjectUtils.isEmpty(password)) {
            return ApiRestResponse.error(MallExceptionEnum.NEED_PASSWORD);
        }

        User user = userService.login(userName, password);

        // 判断身份是否是管理员
        if (userService.checkAdinRole(user)) {
            // 是管理员 执行操作
            user.setPassword(null);
            // user对象放入session
            session.setAttribute(Constant.MALL_USER, user);
            return ApiRestResponse.success(user);
        } else {
            return ApiRestResponse.error(MallExceptionEnum.NEED_ADMIN);
        }


    }
}
