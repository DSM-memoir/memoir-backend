package com.memoir.domain.memoir.controller.response;

import com.memoir.domain.memoir.entity.Memoir;
import lombok.Builder;

import java.util.List;

@Builder
public class MemoirFindAllResponse{
  final List<Memoir> memoirs;
}
