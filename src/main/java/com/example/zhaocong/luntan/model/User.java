package com.example.zhaocong.luntan.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String user_id;
    private String userName;
    private String token;
    private Date createTime;
    private Date modifyTime;
    private String photo_img;
    private String bio;

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

    public User(String user_id, String userName, String token, Date createTime, Date modifyTime, String photo_img) {
        this.user_id = user_id;
        this.userName = userName;
        this.token = token;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.photo_img = photo_img;
    }

    public User() {
    }
}
