package com.memoir.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalErrorCode implements ErrorProperty  {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "Bad request", "1"),

    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed", "1"),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error", "1")
    ;

    private final Integer status;
    private final String message;
    private final String sequence;

    GlobalErrorCode(Integer status, String message, String sequence) {
        this.status = status;
        this.message = message;
        this.sequence = sequence;
    }

    @Override
    public Integer status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    @Override
    public String code() {
        return "GLOBAL-" + status + "-" + sequence;
    }
}