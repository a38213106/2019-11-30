package com.example.zhaocong.luntan.exception;

public enum CustomerizeErrorCode implements ICustomerizeExceptionCode {

    QUESTION_NOT_FOUND(2001,"你找到问题不在了，要不要换个试试？"),
    SYS_ERROR(2002,"服务冒烟了，要不然你稍后再试试！！！"),
    TARGET_QUESTION_NOT_FOUND(2003,"你评论的问题不在了，请稍后再试");

    private String message;
    private Integer code;

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }


    CustomerizeErrorCode(Integer code,String message){
        this.code=code ;
        this.message=message;
    }
}
