package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class IsNotMine extends BusinessBaseException {
  public IsNotMine(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public IsNotMine() {
    super(MemoirErrorCode.IS_NOT_MINE);
  }
}
