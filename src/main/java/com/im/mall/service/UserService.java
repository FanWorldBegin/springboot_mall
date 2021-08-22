package com.im.mall.service;

import com.im.mall.exception.MallException;
import com.im.mall.model.pojo.User;

public interface UserService {
    User getUser();

    void register(String userName, String password) throws MallException;

    // 直接在实现类中写
    User login(String userName, String password) throws MallException;

    void updateInformation(User user) throws MallException;

    boolean checkAdinRole(User user);
}
