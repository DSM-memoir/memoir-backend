package com.memoir.domain.memoir.controller.response;

import com.memoir.domain.memoir.entity.Memoir;
import lombok.Builder;

import java.util.List;
import lombok.Getter;

@Builder
@Getter
public class MemoirFindAllResponse{
  final List<Memoir> memoirs;
}
