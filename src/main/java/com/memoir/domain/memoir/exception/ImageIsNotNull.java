package com.memoir.domain.memoir.exception;

import com.memoir.global.exception.BusinessBaseException;
import com.memoir.global.exception.ErrorProperty;

public class ImageIsNotNull extends BusinessBaseException {
  public ImageIsNotNull(ErrorProperty errorCode) {
    super(errorCode.message(), errorCode);
  }

  public ImageIsNotNull() {
    super(MemoirErrorCode.IMAGE_IS_NOT_NULL);
  }
}
