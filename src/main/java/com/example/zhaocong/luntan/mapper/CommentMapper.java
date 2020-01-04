package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {

    void insertComment(@Param(value="c") Comment c);

    Comment getComment(Integer parent_id);
}
