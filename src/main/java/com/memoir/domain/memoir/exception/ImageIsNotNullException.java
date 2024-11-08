package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class ImageIsNotNullException extends BusinessBaseException {
  public ImageIsNotNullException(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public ImageIsNotNullException() {
    super(MemoirErrorCode.IMAGE_IS_NOT_NULL);
  }
}
