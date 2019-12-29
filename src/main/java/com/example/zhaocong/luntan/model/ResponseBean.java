package com.example.zhaocong.luntan.model;

import lombok.Data;

@Data
public class  ResponseBean<T> {
     private String statusCode;
     private String msg;
     private T data;

    public ResponseBean(String statusCode, String msg, T data) {
        this.statusCode = statusCode;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseBean success(String msg,T object){
        return new ResponseBean<T>(StatusCode.HTTP_OK.getIndex(),msg,object);
    }
}
