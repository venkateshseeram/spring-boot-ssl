package com.mkyong;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import retrofit2.HttpException;

import java.io.IOException;
import java.util.concurrent.CompletionException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value= Exception.class)
    protected ResponseEntity<Object> handleConflict(
            Exception exception,
            WebRequest request) throws IOException {
        String bodyOfResponse = "This should be application specific";



   /*     if(exception instanceof HttpException){

            HttpException httpException= (HttpException) exception;

            return handleExceptionInternal(httpException, httpException.response().errorBody().string(),
                    new HttpHeaders(), HttpStatus.valueOf(httpException.code()), request);


        }*/


        return handleExceptionInternal(exception, bodyOfResponse,new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
