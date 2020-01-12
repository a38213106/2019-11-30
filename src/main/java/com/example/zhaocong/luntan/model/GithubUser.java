package com.example.zhaocong.luntan.model;


import lombok.Data;

/**
 * 引用lombok的插件，实体类的GET和SET方法不用写了
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;

}
