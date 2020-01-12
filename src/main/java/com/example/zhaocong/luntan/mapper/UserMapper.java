package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    User findUserByToken(String token);

    User findUserByCreator(@Param("creator") String creator);

    User findUserByUserId(@Param("user_id") String user_id);

    void updateUser(User subjectUser);

    List<User> findUserByUserIds(@Param(value="userIds") List<String> userIds);
}
