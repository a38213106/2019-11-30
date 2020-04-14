package com.example.zhaocong.luntan.model;

import lombok.Data;

import java.sql.Date;


@Data
public class Employees {
    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private Integer salary;
    private Double commisionPct;
    private Integer managerId;
    private Integer departmentId;
}
