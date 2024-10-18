package com.memoir.domain.user.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class PasswordNotMatch extends BusinessBaseException {
    public PasswordNotMatch(ErrorProperty errorCode) {
        super(errorCode.message(), errorCode);
    }

    public PasswordNotMatch() {
        super(UserErrorCode.PASSWORD_NOT_MATCH);
    }
}
