package com.example.zhaocong.luntan.service;

import com.example.zhaocong.luntan.mapper.OracleMapper;
import com.example.zhaocong.luntan.model.Employees;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OracleService {
    @Autowired
    private OracleMapper oracleMapper;

    public List<Employees> getEmployees() {
          return  oracleMapper.getEmployees();
    }
}
