package com.example.zhaocong.luntan.controller;


import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Controller
public class Index {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value="/")
    public String index(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user=userMapper.findUserByToken(token);
                if(user!=null) {
                    request.getSession().setAttribute("user", user);
                    break;
                }
            }
        }
        return "index";
    }
}
