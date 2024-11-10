package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.response.MemoirDetailDTO;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemoirFindByUUID {
  private final MemoirRepository memoirRepository;
  private final SecurityService securityService;

  public MemoirDetailDTO execute(UUID memoirId){
      return memoirRepository.findDetailById(
              memoirId,
              securityService.getCurrentUserId()
      );
  }
}
