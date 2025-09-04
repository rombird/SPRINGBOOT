package com.example.demo.Controller.GlobalException;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// 전역 exception관리
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String allExceptionHandler(Exception e){
        log.info("Global Exception Handler ... " + e);
        return "global_error";
    }
}
