package com.example.zhaocong.luntan.controller;



import com.example.zhaocong.luntan.model.QuestionPageList;
import com.example.zhaocong.luntan.model.ResponseBean;
import com.example.zhaocong.luntan.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class Index {

    @Autowired
    private QuestionDtoService questionDtoService;

    @GetMapping(value="/")
    public String index( Model model,
         @RequestParam(name="pageNo", defaultValue="1") Integer pageNo ,@RequestParam(name="pageSize",defaultValue="3") Integer pageSize
    ){

        QuestionPageList questionDtoList=  questionDtoService.getList(pageNo,pageSize);

        model.addAttribute("questionDtoLists",questionDtoList);
        return "index";
    }

}
