package com.memoir.domain.user.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class UserAccountIdAlreadyExistsException extends BusinessBaseException {
    public UserAccountIdAlreadyExistsException(ErrorProperty errorCode) {
        super(errorCode.message(), errorCode);
    }

    public UserAccountIdAlreadyExistsException() {
        super(UserErrorCode.USER_ACCOUNT_ID_ALREADY_EXISTS);
    }
}
