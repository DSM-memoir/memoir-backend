package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class NotFoundMemoir extends BusinessBaseException {
  public NotFoundMemoir(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public NotFoundMemoir() {
    super(MemoirErrorCode.NOT_FOUND_MEMOIR);
  }
}
