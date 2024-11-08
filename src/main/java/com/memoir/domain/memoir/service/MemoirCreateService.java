package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.request.MemoirCreateRequest;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.exception.ImageIsNotNullException;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import com.memoir.global.storage.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemoirCreateService {
  private final S3Service s3Service;
  private final MemoirRepository memoirRepository;
  private final SecurityService securityService;

  public void execute(MemoirCreateRequest request, MultipartFile image) {
    if (image == null || image.isEmpty()) {
      throw new ImageIsNotNullException();
    }

    final String imageUrl = s3Service.uploadFile(image);

    Memoir memoir = Memoir.builder()
            .title(request.getTitle())
            .author(securityService.getCurrentUser())
            .content(request.getContent())
            .feels(request.getFeels())
            .imageUrl(imageUrl)
            .published(request.isPublished())
            .build();

    memoirRepository.save(memoir);
  }
}