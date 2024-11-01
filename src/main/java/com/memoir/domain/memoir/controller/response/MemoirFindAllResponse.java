package com.memoir.domain.memoir.controller.response;

import lombok.Builder;

import java.util.List;
import lombok.Getter;

@Builder
@Getter
public class MemoirFindAllResponse{
  final List<MemoirFindResponse> memoirs;
}
