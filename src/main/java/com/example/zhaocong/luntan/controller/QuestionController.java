package com.example.zhaocong.luntan.controller;

import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.model.QuestionDto;
import com.example.zhaocong.luntan.service.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    private QuestionDtoService questionDtoService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id") Integer id,Model model){
        QuestionDto questionDto=questionDtoService.getQuestionById(id);
        model.addAttribute("question",questionDto);
        return "question";
    }
}
