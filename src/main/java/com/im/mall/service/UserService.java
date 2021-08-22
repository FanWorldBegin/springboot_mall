package com.im.mall.service;

import com.im.mall.exception.MallException;
import com.im.mall.model.pojo.User;

public interface UserService {
    User getUser();

    void register(String userName, String password) throws MallException;
}
