package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class NotFoundMemoirException extends BusinessBaseException {
  public NotFoundMemoirException(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public NotFoundMemoirException() {
    super(MemoirErrorCode.NOT_FOUND_MEMOIR);
  }
}
