package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.ErrorProperty;
import org.springframework.http.HttpStatus;

public enum MemoirErrorCode implements ErrorProperty {
    IMAGE_IS_NOT_NULL(HttpStatus.BAD_REQUEST.value(), "Image is not null", 1);

    private final Integer status;
    private final String message;
    private final Integer sequence;

    MemoirErrorCode(Integer status, String message, Integer sequence) {
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
        return "MEMOIR-" + status + "-" + sequence;
    }
}
