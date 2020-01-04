package com.example.zhaocong.luntan.exception;


import com.example.zhaocong.luntan.model.ResponseBean;
import com.example.zhaocong.luntan.model.StatusCode;

public class CustomerizeException extends RuntimeException {
    private String message;
    private  Integer code;

    @Override
    public String getMessage() {
        return message;
    }


    public Integer getCode() {
        return code;
    }

    public CustomerizeException(ICustomerizeExceptionCode errCode){
            this.message=errCode.getMessage();
            this.code=errCode.getCode();
    }

    public CustomerizeException(String message){
        this.message=message;

    }
}
