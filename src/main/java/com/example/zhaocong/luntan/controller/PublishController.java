package com.example.zhaocong.luntan.controller;


import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.Question;
import com.example.zhaocong.luntan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private Question question;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping(value="/publish")
    public  String publish(){
        return "publish";
    }

    @PostMapping(value="/publish")
    public String questionPublish(@RequestParam("title") String title,@RequestParam("description") String description,@RequestParam("tag") String tag, HttpServletRequest request, Model model){
        Cookie[] cookies=request.getCookies();
        User user=null;
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                user=userMapper.findUserByToken(token);
                if(user!=null) {
                    request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }

        if(user==null){
            model.addAttribute("error","账号未登陆");
            return "publish";
        }

        Question question=new Question();
        question.setCreator(Integer.parseInt(user.getUser_id()));
        question.setGmt_create(new Date());
        question.setGmt_modify(new Date());
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        questionMapper.insertQuestion(question);
        return "redirect:/";
    }
}
