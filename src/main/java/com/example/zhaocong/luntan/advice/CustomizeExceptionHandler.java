package com.example.zhaocong.luntan.advice;


import com.example.zhaocong.luntan.exception.CustomerizeErrorCode;
import com.example.zhaocong.luntan.exception.CustomerizeException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Logger;


/**
 * Created by codedrinker on 2019/5/28.
 */
@ControllerAdvice
@Slf4j
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model) {
        if(e instanceof CustomerizeException) {
            log.error(e.getMessage());
            model.addAttribute("message", e.getMessage());
        }else{
            model.addAttribute("message", CustomerizeErrorCode.SYS_ERROR.getMessage());
        }

        return new ModelAndView("error");
        }
    }

