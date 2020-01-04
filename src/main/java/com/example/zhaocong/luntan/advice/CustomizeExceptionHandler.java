package com.example.zhaocong.luntan.advice;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONPObject;
import com.example.zhaocong.luntan.exception.CustomerizeErrorCode;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import com.example.zhaocong.luntan.model.ResponseBean;
import com.example.zhaocong.luntan.model.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


/**
 * author zhaocong
 * 获取页面请求接口报的错误
 */
@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model,HttpServletRequest request,HttpServletResponse response) {
        String contentType=request.getContentType();
        if(contentType.equals("application/json")){
            //处理JSON格式的请求错误
            if(e instanceof CustomerizeException) {

                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.write(JSON.toJSONString(ResponseBean.error(StatusCode.TARGET_QUESTION_NOT_FOUND.getName(),StatusCode.TARGET_QUESTION_NOT_FOUND.getIndex())));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

                writer.close();
            }else{

                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = null;
                try {
                    writer = response.getWriter();
                    writer.write(JSON.toJSONString( ResponseBean.error(StatusCode.SYS_ERROR.getName(),StatusCode.SYS_ERROR.getIndex())));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                writer.close();
            }

            return  null;
        }else{
            //处理异常返回是页面的
            if(e instanceof CustomerizeException) {

                model.addAttribute("message", e.getMessage());
            }else{
                model.addAttribute("message", CustomerizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}

