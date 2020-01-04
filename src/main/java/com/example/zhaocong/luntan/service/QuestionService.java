package com.example.zhaocong.luntan.service;


import com.example.zhaocong.luntan.exception.CustomerizeErrorCode;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    public Question findQuestionById(Integer id) {
        return questionMapper.getQuestionById(id);
    }

    public void insertQuestion(Question question) {
        questionMapper.insertQuestion(question);
    }

    public void createOrupdate(Question question)  {
        if(question.getId()==null){
              questionMapper.insertQuestion(question);
        }else{
            question.setGmt_modify(new Date());
           int update= questionMapper.updateQuestion(question);
            if (update != 1) {
                throw new CustomerizeException(CustomerizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView_count(Integer id) {

    }
}
