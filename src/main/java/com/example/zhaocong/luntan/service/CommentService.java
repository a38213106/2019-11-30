package com.example.zhaocong.luntan.service;


import com.example.zhaocong.luntan.enums.CommentTypeEnum;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import com.example.zhaocong.luntan.mapper.CommentMapper;
import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Transactional
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

            Question question = questionMapper.getQuestionById(dbComment.getParent_id());
            if (question == null) {
                throw new CustomerizeException(StatusCode.QUESTION_NOT_FOUND.getName());
            }

            commentMapper.insertComment(comment);
            Comment commentParent = new Comment();
            commentParent.setComment_count(1);
            commentParent.setId(comment.getParent_id());
            commentMapper.updateComment_count(commentParent);

        } else {
            //评论问题

            Question question = questionMapper.getQuestionById(comment.getParent_id());
            if (question == null) {
                throw new CustomerizeException(StatusCode.QUESTION_NOT_FOUND.getName());
            }

            comment.setComment_count(0);
            commentMapper.insertComment(comment);
            question.setComment_count(1);
            questionMapper.updateComment_count(question);
        }
    }

    public List<CommentDTO> getCommentListByQuestionId(Integer id,CommentTypeEnum type) {
        List<Comment> comments = commentMapper.getCommentListByParentId(id, type.getType());
        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Integer> commentors = comments.stream().map(comment -> comment.getCommentor()).collect(Collectors.toSet());
        List<String> userIds = new ArrayList<>();
        for (Integer comment : commentors) {
            userIds.add(String.valueOf(comment));
        }

        List<User> users = userMapper.findUserByUserIds(userIds);
        Map<String, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getUser_id(), user -> user));

        // 转换 comment 为 commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            commentDTO.setUser(userMap.get(comment.getCommentor()+""));
            return commentDTO;
        }).collect(Collectors.toList());

        return commentDTOS;
    }
}
