package com.im.mall.model.dao;

import com.im.mall.model.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table imooc_mall_user
     *
     * @mbg.generated Fri Aug 20 18:45:29 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}