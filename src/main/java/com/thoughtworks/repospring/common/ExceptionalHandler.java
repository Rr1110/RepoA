package com.thoughtworks.repospring.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionalHandler {

    @ExceptionHandler(ProductNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResult productNotExistHandle(ProductNotExistException e){
        return ErrorResult.builder().message(e.getMessage()).build();
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResult handle(Exception e) {
        return ErrorResult.builder()
                .message(e.getMessage())
                .build();
    }
}
