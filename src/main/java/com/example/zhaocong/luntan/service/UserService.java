package com.example.zhaocong.luntan.service;


import com.example.zhaocong.luntan.mapper.UserMapper;
import com.example.zhaocong.luntan.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void createOrupdate(User user) {
        User subjectUser=userMapper.findUserByUserId(user.getUser_id());

        if(subjectUser!=null){
            userMapper.updateUser(user);
        }else{
            userMapper.insertUser(user);
        }
    }
}

