package com.memoir.global.exception;

public class BusinessBaseException extends RuntimeException {

    private final ErrorProperty errorCode;

    public BusinessBaseException(String message, ErrorProperty errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessBaseException(ErrorProperty errorCode) {
        super(errorCode.message());
        this.errorCode = errorCode;
    }

    public ErrorProperty getErrorCode() {
        return errorCode;
    }
}
