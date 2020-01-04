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

    public ResponseBean(String statusCode, String msg) {
        this.statusCode = statusCode;
        this.msg = msg;
    }


    public static <T> ResponseBean success(T object){
        return new ResponseBean<T>(StatusCode.HTTP_OK.getIndex(),StatusCode.HTTP_OK.getName(),object);
    }

    public static  <T> ResponseBean error(String msg,String statusCode){
        return  new ResponseBean<T>(msg,statusCode);
    }
}
