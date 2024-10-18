package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirFindAllService {
  private final MemoirRepository memoirRepository;

  public MemoirFindAllResponse execute() {
    final List<Memoir> memoirs = memoirRepository.findAll();
    return MemoirFindAllResponse.builder().memoirs(memoirs).build();
  }
}
