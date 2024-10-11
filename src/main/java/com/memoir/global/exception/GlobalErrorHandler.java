package com.memoir.global.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpServerErrorException e) {
        return createErrorResponseEntity(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handle(HttpRequestMethodNotSupportedException e) {
        return createErrorResponseEntity(GlobalErrorCode.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        return createErrorResponseEntity(GlobalErrorCode.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessBaseException.class)
    protected ResponseEntity<ErrorResponse> handle(BusinessBaseException e) {
        return createErrorResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handle(Exception e) {
        e.printStackTrace();
        return createErrorResponseEntity(GlobalErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> createErrorResponseEntity(ErrorProperty errorCode) {
        return new ResponseEntity<>(
                ErrorResponse.of(errorCode),
                HttpStatusCode.valueOf(errorCode.status()));
    }
}
