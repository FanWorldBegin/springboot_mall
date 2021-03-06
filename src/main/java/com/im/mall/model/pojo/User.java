package com.im.mall.model.pojo;

import java.util.Date;

public class User {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.id
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.username
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.password
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.personalized_signature
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private String personalizedSignature;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.role
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private Integer role;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.create_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column imooc_mall_user.update_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.id
     *
     * @return the value of imooc_mall_user.id
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.id
     *
     * @param id the value for imooc_mall_user.id
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.username
     *
     * @return the value of imooc_mall_user.username
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.username
     *
     * @param username the value for imooc_mall_user.username
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.password
     *
     * @return the value of imooc_mall_user.password
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.password
     *
     * @param password the value for imooc_mall_user.password
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.personalized_signature
     *
     * @return the value of imooc_mall_user.personalized_signature
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public String getPersonalizedSignature() {
        return personalizedSignature;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.personalized_signature
     *
     * @param personalizedSignature the value for imooc_mall_user.personalized_signature
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setPersonalizedSignature(String personalizedSignature) {
        this.personalizedSignature = personalizedSignature == null ? null : personalizedSignature.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.role
     *
     * @return the value of imooc_mall_user.role
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.role
     *
     * @param role the value for imooc_mall_user.role
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.create_time
     *
     * @return the value of imooc_mall_user.create_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.create_time
     *
     * @param createTime the value for imooc_mall_user.create_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column imooc_mall_user.update_time
     *
     * @return the value of imooc_mall_user.update_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column imooc_mall_user.update_time
     *
     * @param updateTime the value for imooc_mall_user.update_time
     *
     * @mbg.generated Sat Sep 04 17:20:21 CST 2021
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}