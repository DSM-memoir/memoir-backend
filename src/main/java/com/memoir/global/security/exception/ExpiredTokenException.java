package com.memoir.global.security.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class ExpiredTokenException extends BusinessBaseException {
    public ExpiredTokenException(String message, ErrorProperty errorCode) {
        super(message, errorCode);
    }

    public ExpiredTokenException() {
        super(SecurityErrorCode.EXPIRED_TOKEN);
    }
}
