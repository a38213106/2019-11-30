package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Comment;
import com.example.zhaocong.luntan.model.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(@Param(value="c") Comment c);

    Comment getComment(Integer parent_id);

    void updateComment_count(Comment commentParent);

    List<Comment> getCommentListByParentId(@Param(value="id") Integer id,@Param(value="type") Integer type);
}
