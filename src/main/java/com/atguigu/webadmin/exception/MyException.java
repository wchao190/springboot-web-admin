package com.atguigu.webadmin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class MyException   {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})
    public String globalExceptionHandle(){
        return "main";
    }
}
