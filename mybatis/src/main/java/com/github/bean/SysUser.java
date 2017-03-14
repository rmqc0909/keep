package com.github.bean;

import java.sql.Timestamp;

/**
 * Created by xiedan on 2017/3/2.
 */
public class SysUser {
    /**
     *
     */
    private int userId;

    /**
     *
     */
    private String userPassword;

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String nickName;

    /**
     *
     */
    private int userTypeId;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private int isValid;

    /**
     *
     */
    private Timestamp createdTime;

    /**
     *
     */
    private Timestamp updateTime;

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String eamil) {
        this.email = eamil;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public SysUser() {
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", userTypeId=" + userTypeId +
                ", email='" + email + '\'' +
                ", isValid=" + isValid +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
