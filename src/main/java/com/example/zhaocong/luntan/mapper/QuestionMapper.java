package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {

    @Insert({"insert into question (title,description,gmt_create,gmt_modify,creator,comment_count,view_count,praise,tag) values(#{title},#{description},#{gmt_create},#{gmt_modify},#{creator},#{comment_count},#{view_count},#{praise},#{tag})"})
    void insertQuestion(Question question);

    @Select({"select title,description,gmt_create,gmt_modify,creator,ifnull(comment_count,0) comment_count,ifnull(view_count,0) view_count,praise,tag from question limit #{offest},#{pageSize}"})
    List<Question> findAll(@Param(value="offest") int offest,@Param(value="pageSize") Integer pageSize);

    @Select("select count(1) from question")
    Integer totalCount();

    @Select({"select title,description,gmt_create,gmt_modify,creator,ifnull(comment_count,0) comment_count,ifnull(view_count,0) view_count,praise,tag from question where creator=#{user_id} limit #{offest},#{pageSize}"})
    List<Question> findAllByUserId(@Param(value="user_id") String user_id, @Param(value="offest") int offest,@Param(value="pageSize") Integer pageSize);

    @Select("select count(1) from question where creator=#{user_id}")
    Integer totalCountByUserId(@Param(value="user_id") String user_id);
}
