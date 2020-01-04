package com.example.zhaocong.luntan.service;

import com.example.zhaocong.luntan.exception.CustomerizeErrorCode;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import com.example.zhaocong.luntan.mapper.QuestionMapper;
import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.Question;
import com.example.zhaocong.luntan.model.QuestionDto;
import com.example.zhaocong.luntan.model.QuestionPageList;
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

    public QuestionPageList getList(Integer pageNo, Integer pageSize) {
        QuestionPageList questionPageList = new QuestionPageList();
        int totalCount = questionMapper.totalCount();
        Integer totalPage;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        questionPageList.PageSet(totalPage, pageNo);

        int offest = pageSize * (pageNo - 1);   //mysql分页的偏移量

        List<QuestionDto> list = new ArrayList<QuestionDto>();

        for (Question question : questionMapper.findAll(offest, pageSize)) {
            User user = userMapper.findUserByCreator(question.getCreator() + "");
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            list.add(questionDto);
        }
        questionPageList.setQuestionData(list);

        return questionPageList;
    }

    public QuestionPageList getMyQuestion(String user_id, Integer pageNo, Integer pageSize) {
        QuestionPageList questionPageList = new QuestionPageList();
        int totalCount = questionMapper.totalCountByUserId(user_id);
        Integer totalPage;
        if (totalCount % pageSize == 0) {
            totalPage = totalCount / pageSize;
        } else {
            totalPage = totalCount / pageSize + 1;
        }

        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo > totalPage) {
            pageNo = totalPage;
        }

        questionPageList.PageSet(totalPage, pageNo);

        int offest = pageSize * (pageNo - 1);   //mysql分页的偏移量

        List<QuestionDto> list = new ArrayList<QuestionDto>();

        for (Question question : questionMapper.findAllByUserId(user_id, offest, pageSize)) {
            User user = userMapper.findUserByCreator(question.getCreator() + "");
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            list.add(questionDto);
        }
        questionPageList.setQuestionData(list);

        return questionPageList;
    }

    public QuestionDto getQuestionById(Integer id) {
        //点击详情问题详情页面，增加阅读数
        Question incQuestion = new Question();
        incQuestion.setId(id);
        incQuestion.setView_count(1);
        questionMapper.updateView_count(incQuestion);
        Question question = questionMapper.getQuestionById(id);
        if (question == null) {
            throw new CustomerizeException(CustomerizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDto questionDto = new QuestionDto();
        BeanUtils.copyProperties(question, questionDto);

        User user = userMapper.findUserByCreator(question.getCreator() + "");
        questionDto.setUser(user);
        return questionDto;
    }
}
