package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper {

    @Insert({"insert into question (title,description,gmt_create,gmt_modify,creator,comment_count,view_count,praise,tag) values(#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{comment_count},#{view_count},#{praise},#{tag})"})
    void insertQuestion(Question question);
}
