package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    void insertQuestion(Question question);

    List<Question> findAll(@Param(value="offest") int offest,@Param(value="pageSize") Integer pageSize);

    Integer totalCount();

    List<Question> findAllByUserId(@Param(value="user_id") String user_id, @Param(value="offest") int offest,@Param(value="pageSize") Integer pageSize);

    Integer totalCountByUserId(@Param(value="user_id") String user_id);

    Question getQuestionById(@Param(value="id") Integer id);

    int updateQuestion(Question question);

    void updateView_count(Question question);
}
