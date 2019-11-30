package com.example.zhaocong.luntan.controller;

import com.example.zhaocong.luntan.model.AccessToken;
import com.example.zhaocong.luntan.model.GithubUser;
import com.example.zhaocong.luntan.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    @GetMapping(value="/callback")
    public String callback(@RequestParam(name="code") String code,@RequestParam(name="state") String state){
        String token=githubProvider.getAccessToken(new AccessToken(client_id,client_secret,code,redirect_uri,state));
        GithubUser user=githubProvider.getUser(token);
        System.out.println(user.toString());
        return "index";
    }
}
