package com.example.zhaocong.luntan.model;

import lombok.Data;

@Data
public class  ResponseBean<T> {
     private String statusCode;
     private String message;
     private T data;

    public ResponseBean(String message, String statusCode, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ResponseBean(String message, String statusCode) {
        this.statusCode = statusCode;
        this.message = message;
    }


    public static <T> ResponseBean success(T object){
        return new ResponseBean<T>(StatusCode.HTTP_OK.getName(),StatusCode.HTTP_OK.getIndex(),object);
    }

    public static  <T> ResponseBean error(String message,String statusCode){
        return  new ResponseBean<T>(message,statusCode);
    }
}
