package com.memoir.global.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private String message;
    private String code;

    private ErrorResponse(final ErrorProperty code) {
        this.message = code.message();
        this.code = code.code();
    }

    public ErrorResponse(final ErrorProperty code, final String message) {
        this.message = message;
        this.code = code.code();
    }

    public static ErrorResponse of(final ErrorProperty code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorProperty code, final String message) {
        return new ErrorResponse(code, message);
    }
}
