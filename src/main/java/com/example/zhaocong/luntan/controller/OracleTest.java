package com.example.zhaocong.luntan.controller;


import com.example.zhaocong.luntan.model.Employees;
import com.example.zhaocong.luntan.model.ResponseBean;
import com.example.zhaocong.luntan.service.OracleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class OracleTest {

    @Autowired
    private OracleService oracleService;

    @ResponseBody
    @GetMapping(value="/hello")
    public ResponseBean hello(){
        try{
        List<Employees> employees = oracleService.getEmployees();
        return ResponseBean.success(employees);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.error("接口错误","500");
        }
    }
}
