package com.memoir.domain.memoir.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemoirCreateRequest {
  @NotNull
  private String title;

  @NotNull
  private String content;

  @NotNull
  private String feels;

  @NotNull
  private boolean published;
}