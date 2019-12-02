package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert({"insert into user(userid,username,createTime,modifyTime,token) values(#{user_id},#{userName},#{createTime},#{modifyTime},#{token})"})
    void insertUser(User user);

    @Select({"select userid,username,token,createtime,modifytime from user where token=#{token}"})
    User findUserByToken(@Param("token") String token);
}
