package com.memoir.domain.user.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class UserNotFoundException extends BusinessBaseException {
    public UserNotFoundException(ErrorProperty errorCode) {
        super(errorCode.message(), errorCode);
    }

    public UserNotFoundException() {
        super(UserErrorCode.USER_NOT_FOUND);
    }
}
