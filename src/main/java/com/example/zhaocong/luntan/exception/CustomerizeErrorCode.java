package com.example.zhaocong.luntan.exception;

public enum CustomerizeErrorCode implements ICustomerizeExceptionCode {

    QUESTION_NOT_FOUND("你找到问题不在了，要不要换个试试？"),
    SYS_ERROR("服务冒烟了，要不然你稍后再试试！！！");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomerizeErrorCode(String message){
        this.message=message;
    }
}
