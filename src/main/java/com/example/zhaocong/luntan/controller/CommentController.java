package com.example.zhaocong.luntan.controller;





import com.example.zhaocong.luntan.model.Comment;
import com.example.zhaocong.luntan.model.ResponseBean;
import com.example.zhaocong.luntan.model.StatusCode;
import com.example.zhaocong.luntan.model.User;
import com.example.zhaocong.luntan.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


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
}
