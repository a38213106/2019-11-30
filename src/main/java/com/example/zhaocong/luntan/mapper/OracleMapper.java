package com.example.zhaocong.luntan.mapper;

import com.example.zhaocong.luntan.model.Employees;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OracleMapper {

    List<Employees> getEmployees() ;

}
