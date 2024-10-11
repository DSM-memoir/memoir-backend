package com.memoir.global.security.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class NotFoundException extends BusinessBaseException {
    public NotFoundException(String message, ErrorProperty errorCode) {
        super(message, errorCode);
    }

    public NotFoundException() {
        super(SecurityErrorCode.NOT_FOUND);
    }
}
