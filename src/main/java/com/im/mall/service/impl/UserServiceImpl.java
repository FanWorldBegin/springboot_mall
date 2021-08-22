package com.im.mall.service.impl;

import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.dao.UserMapper;
import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService 实现类
 * 查询数据库返回真正的数据信息
 * service层不处理最终返回信息
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

    @Override
    public void register(String userName, String password) throws MallException {
        // 查询用户名是否存在，不允许重名
        User result = userMapper.selectByName(userName);
        if(result != null) {
            // 用户已经存在
            throw new MallException(MallExceptionEnum.NAME_EXISTED);
        }

        // 写到数据库
        // 创建对象并赋值
        User user = new User();
        user.setUsername(userName);
        user.setPassword(password);
        // insertSelective 仅修改存在的字段， 返回修改成功的条数
        int count = userMapper.insertSelective(user);
        // 插入失败
        if(count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }
}
