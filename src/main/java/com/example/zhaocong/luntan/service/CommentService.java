package com.example.zhaocong.luntan.service;


import com.example.zhaocong.luntan.enums.CommentTypeEnum;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import com.example.zhaocong.luntan.mapper.CommentMapper;
import com.example.zhaocong.luntan.model.Comment;
import com.example.zhaocong.luntan.model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public void insertComment(Comment comment) {
        if (comment.getParent_id() == null || comment.getParent_id() == 0) {
            throw new CustomerizeException(StatusCode.TARGET_QUESTION_NOT_FOUND.getName());
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomerizeException(StatusCode.COMMENT_TYPE_PARAM.getName());
        }

        if (CommentTypeEnum.Comment.getType() == comment.getType()) {
            //评论回复
            Comment dbComment = commentMapper.getComment(comment.getParent_id());

            if (dbComment == null) {
                throw new CustomerizeException(StatusCode.COMMENT_NOT_FOUND.getName());
            }
            commentMapper.insertComment(comment);
        } else {
            //评论问题

        }
    }
}
