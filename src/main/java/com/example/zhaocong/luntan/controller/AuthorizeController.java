package com.example.zhaocong.luntan.controller;

import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.AccessToken;
import com.example.zhaocong.luntan.model.GithubUser;
import com.example.zhaocong.luntan.model.User;
import com.example.zhaocong.luntan.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private UserMapper userMapper;


    @GetMapping(value="/callback")
    public String callback(@RequestParam(name="code") String code, @RequestParam(name="state") String state, HttpServletResponse response){

        //调GITHUB的登陆
        String getToken=githubProvider.getAccessToken(new AccessToken(client_id,client_secret,code,redirect_uri,state));
        GithubUser githubUser=githubProvider.getUser( getToken);

        if(githubUser!=null){
            //登陆成功，写SESSION和COOKIE
            String token = UUID.randomUUID().toString();
            userMapper.insertUser(new User(String.valueOf(githubUser.getId()),githubUser.getName(),token,new Date(),new Date(),githubUser.getAvatarUrl()));
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        }else{
            //登陆失败，跳转登陆页面
            return "redirect:/";
        }
    }
}
