package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert({"insert into user(userid,username,createTime,modifyTime,token,photo_img) values(#{user_id},#{userName},#{createTime},#{modifyTime},#{token},#{photo_img})"})
    void insertUser(User user);

    @Select({"select userid as user_id,username,token,createtime,modifytime from user where token=#{token}"})
    User findUserByToken(@Param("token") String token);

    @Select({"select userid as user_id,username,token,createtime,modifytime,photo_img  from user where userId=#{creator} ORDER BY CREATETIME desc limit 0,1"})
    User findUserByCreator(@Param("creator") String creator);
}
