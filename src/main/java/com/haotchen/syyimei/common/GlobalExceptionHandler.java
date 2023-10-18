package com.haotchen.syyimei.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public R myError(RuntimeException e){
        return R.fail(e.getMessage(),400);
    }
    @ExceptionHandler(Exception.class)
    public R unKonwError(Exception e){
        log.error(e.getMessage());
        return R.fail("网络开小差了~~",500);
    }
}


