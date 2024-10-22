package com.memoir.global.security.exception;

import com.memoir.global.exception.ErrorProperty;
import org.springframework.http.HttpStatus;

public enum SecurityErrorCode implements ErrorProperty {

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Expired token", 1),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "Invalid token", 2),

    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "Can not access", 1),

    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "Not Found", 1)
    ;

    private final Integer status;
    private final String message;
    private final Integer sequence;

    SecurityErrorCode(Integer status, String message, Integer sequence) {
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
        return "SECURITY-" + status + "-" + sequence;
    }
}
