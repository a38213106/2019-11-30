package com.example.zhaocong.luntan.service;

import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.Question;
import com.example.zhaocong.luntan.model.QuestionDto;
import com.example.zhaocong.luntan.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionDtoService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDto> getList() {
        List<QuestionDto> list=new ArrayList<QuestionDto>();

        for(Question question:questionMapper.findAll()){
            User user=userMapper.findUserByCreator(question.getCreator()+"");
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            list.add(questionDto);
        }
        return  list;
    }
}
