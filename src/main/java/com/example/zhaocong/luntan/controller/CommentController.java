package com.example.zhaocong.luntan.controller;





import com.example.zhaocong.luntan.enums.CommentTypeEnum;
import com.example.zhaocong.luntan.model.*;
import com.example.zhaocong.luntan.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;


    @ResponseBody
    @PostMapping(value = "/comment")
    public ResponseBean post(@RequestBody Comment comment, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResponseBean.error(StatusCode.USER_NULL.getName(),StatusCode.USER_NULL.getIndex());
        }

        if(comment.getContent()==null || StringUtils.isBlank(comment.getContent())){
            return  ResponseBean.error(StatusCode.COMMENT_NOT_BLANK.getName(),StatusCode.COMMENT_NOT_BLANK.getIndex());
        }

        comment.setGmt_create(new Date());
        comment.setGmt_modify(new Date());
        comment.setCommentor(33488506);
        comment.setLike_count(0L);
        commentService.insertComment(comment);
        return ResponseBean.success("");
    }

    @ResponseBody
    @GetMapping(value="/comment/{id}")
    public ResponseBean comment(@PathVariable(name="id")Integer id){
        List<CommentDTO> comments=commentService.getCommentListByQuestionId(id, CommentTypeEnum.Comment);
        return ResponseBean.success(comments);
    }
}
