package com.memoir.domain.memoir.controller.request;
import lombok.Getter;

@Getter
public class MemoirUpdateRequest {
  private String title;

  private String content;

  private String feels;
}
