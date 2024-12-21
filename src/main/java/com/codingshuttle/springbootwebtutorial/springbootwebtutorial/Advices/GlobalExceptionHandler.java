package com.codingshuttle.springbootwebtutorial.springbootwebtutorial.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(NoSuchElementException exception){
        ApiError apiError=ApiError.builder()
    }
}

