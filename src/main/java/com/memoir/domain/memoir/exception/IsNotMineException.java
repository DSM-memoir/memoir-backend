package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class IsNotMineException extends BusinessBaseException {
  public IsNotMineException(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public IsNotMineException() {
    super(MemoirErrorCode.IS_NOT_MINE);
  }
}
