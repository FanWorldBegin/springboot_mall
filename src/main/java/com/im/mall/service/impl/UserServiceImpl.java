package com.im.mall.service.impl;

import com.im.mall.exception.MallException;
import com.im.mall.exception.MallExceptionEnum;
import com.im.mall.model.dao.UserMapper;
import com.im.mall.model.pojo.User;
import com.im.mall.service.UserService;
import com.im.mall.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

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
        try {
            user.setPassword(MD5Utils.getMD5Str(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // insertSelective 仅修改存在的字段， 返回修改成功的条数
        int count = userMapper.insertSelective(user);
        // 插入失败
        if(count == 0) {
            throw new MallException(MallExceptionEnum.INSERT_FAILED);
        }
    }

    // 直接在实现类中写 - 提示错误可以直接在接口生成对应的方法声明
    @Override
    public User login(String userName, String password) throws MallException {
        String md5Password = null;
        try {
            md5Password = MD5Utils.getMD5Str(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        User user = userMapper.selectLogin(userName, md5Password);

        if(user == null) {
            throw new MallException(MallExceptionEnum.WRONG_PASSWORD);
        }

        return user;
    }

    @Override
    public void updateInformation(User user) throws MallException {
        // 更新个性签名
        int updateCount = userMapper.updateByPrimaryKeySelective(user);

        // 通过主键更新  应该只有一条
        if(updateCount > 1 ) {
            throw new MallException(MallExceptionEnum.UPDATE_FAILED);
        }
    }

    @Override
    public boolean checkAdinRole(User user) {
        // 1 是普通用户 2 是管理员
        return user.getRole().equals(2);
    }
}
