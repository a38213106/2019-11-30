package com.example.zhaocong.luntan.model;


import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    private Integer id;
    private Integer parent_id;
    private Integer type;
    private String content;
    private Integer commentor;
    private Date gmt_create;
    private Date gmt_modify;
    private Long like_count;
    private Integer comment_count;

}
