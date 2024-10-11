package com.memoir.global.security.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class ForbiddenException extends BusinessBaseException {
    public ForbiddenException(ErrorProperty errorCode) {
        super(errorCode.message(), errorCode);
    }

    public ForbiddenException() {
        super(SecurityErrorCode.FORBIDDEN);
    }
}
