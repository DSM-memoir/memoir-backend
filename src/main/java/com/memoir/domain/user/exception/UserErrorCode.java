package com.memoir.domain.user.exception;

import com.memoir.global.exception.ErrorProperty;
import org.springframework.http.HttpStatus;

public enum UserErrorCode implements ErrorProperty {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "User Not Found", 1),

    USER_ACCOUNT_ID_ALREADY_EXISTS(HttpStatus.CONFLICT.value(), "User Account Id Already Exists", 1)
    ;

    private final Integer status;
    private final String message;
    private final Integer sequence;

    UserErrorCode(Integer status, String message, Integer sequence) {
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
        return "USER-" + status + "-" + sequence;
    }
}
