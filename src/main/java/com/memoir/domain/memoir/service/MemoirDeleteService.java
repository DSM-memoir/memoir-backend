package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.exception.IsNotMine;
import com.memoir.domain.memoir.exception.NotFoundMemoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import com.memoir.global.storage.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemoirDeleteService {
  private final SecurityService securityService;
  private final MemoirRepository memoirRepository;
  private final S3Service s3Service;

  public void execute(UUID memoirId) {
    final UUID userId = securityService.getCurrentUserId();
    final Memoir memoir = memoirRepository.findById(memoirId).orElseThrow(NotFoundMemoir::new);

    if(!memoir.getAuthor().getId().equals(userId)) {
      throw new IsNotMine();
    }

    String imageUrl = memoir.getImageUrl();
    String[] splitImageUrl = imageUrl.split("/");
    String s3Key = splitImageUrl[splitImageUrl.length - 1];

    memoirRepository.deleteById(memoirId);
    s3Service.deleteFile(s3Key);
  }
}
