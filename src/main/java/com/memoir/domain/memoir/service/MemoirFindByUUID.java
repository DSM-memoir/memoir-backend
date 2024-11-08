package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.repository.MemoirRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemoirFindByUUID {
  private final MemoirRepository memoirRepository;

  public void execute(UUID memoirId){

  }
}
