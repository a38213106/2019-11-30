package com.example.zhaocong.luntan.exception;


public class CustomerizeException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomerizeException(ICustomerizeExceptionCode errCode){
            this.message=errCode.getMessage();
    }
}
