package com.memoir.global.security.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class InvalidTokenException extends BusinessBaseException {
    public InvalidTokenException(String message, ErrorProperty errorCode) {
        super(message, errorCode);
    }

    public InvalidTokenException() {
        super(SecurityErrorCode.INVALID_TOKEN);
    }
}
