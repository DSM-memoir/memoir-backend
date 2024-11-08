package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.exception.IsNotMineException;
import com.memoir.domain.memoir.exception.NotFoundMemoirException;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import com.memoir.global.storage.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional()
@RequiredArgsConstructor
public class MemoirDeleteService {
  private final SecurityService securityService;
  private final MemoirRepository memoirRepository;
  private final S3Service s3Service;

  public void execute(UUID memoirId) {
    final UUID userId = securityService.getCurrentUserId();
    final Memoir memoir = memoirRepository.findById(memoirId).orElseThrow(NotFoundMemoirException::new);

    if(!memoir.getAuthor().getId().equals(userId)) {
      throw new IsNotMineException();
    }

    String imageUrl = memoir.getImageUrl();
    String[] splitImageUrl = imageUrl.split("/");
    String s3Key = splitImageUrl[splitImageUrl.length - 1];

    memoirRepository.deleteById(memoirId);
    s3Service.deleteFile(s3Key);
  }
}
