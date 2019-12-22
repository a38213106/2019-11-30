package com.example.zhaocong.luntan.controller;

import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.QuestionPageList;
import com.example.zhaocong.luntan.model.User;
import com.example.zhaocong.luntan.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {

    @Autowired
    private QuestionDtoService questionDtoService;

     @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name="action") String action, Model model, HttpServletRequest request,
                          @RequestParam(name="pageNo", defaultValue="1") Integer pageNo , @RequestParam(name="pageSize",defaultValue="3") Integer pageSize)
     {
           if("questions".equals(action)){
               model.addAttribute("section","questions");
               model.addAttribute("sectionName","我的问题");
           }else if("reply".equals(action)){
               model.addAttribute("section","reply");
               model.addAttribute("sectionName","最新回复");
           }

         User user=(User)request.getSession().getAttribute("user");

         if(user==null){
             return "redirect:/";
         }

         QuestionPageList questionDtoList= questionDtoService.getMyQuestion(user.getUser_id(),pageNo,pageSize);
         model.addAttribute("questionDtoLists",questionDtoList);
           return "profile";
    }
}
