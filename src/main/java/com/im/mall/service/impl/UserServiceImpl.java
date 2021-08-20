package com.im.mall.service.impl;

import com.im.mall.model.dao.UserMapper;
import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService 实现类
 * 查询数据库返回真正的数据信息
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User getUser() {
        // 通过主键查询对象
        return userMapper.selectByPrimaryKey(1);
    }
}
