package com.example.zhaocong.luntan.controller;


import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.QuestionDto;
import com.example.zhaocong.luntan.model.User;
import com.example.zhaocong.luntan.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class Index {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionDtoService questionDtoService;

    @GetMapping(value="/")
    public String index(HttpServletRequest request, Model model){
        Cookie[] cookies=request.getCookies();
        if(cookies!=null && cookies.length!=0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findUserByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                        break;
                    }
                }
            }
        }
        List<QuestionDto> questionDtoList=questionDtoService.getList();
        model.addAttribute("questions",questionDtoList);
        return "index";
    }
}
