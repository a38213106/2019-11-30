package com.example.zhaocong.luntan.model;

import java.util.Date;

public class User {
    private String user_id;
    private String userName;
    private String token;
    private Date createTime;
    private Date modifyTime;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }



    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id='" + user_id + '\'' +
                ", userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public User(String user_id, String userName, String token, Date createTime, Date modifyTime) {
        this.user_id = user_id;
        this.userName = userName;
        this.token = token;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }
}