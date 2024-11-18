package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.request.MemoirUpdateRequest;
import com.memoir.domain.memoir.controller.response.MemoirFindResponse;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.exception.NotFoundMemoirException;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import com.memoir.global.storage.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@Transactional()
@RequiredArgsConstructor
public class MemoirUpdateService {
  private final S3Service s3Service;
  private final MemoirFacade memoirFacade;
  private final SecurityService securityService;
  private final MemoirRepository memoirRepository;

  public MemoirFindResponse execute(UUID memoirId, MemoirUpdateRequest memoirUpdateRequest, MultipartFile image) {
    UUID currentUserId = securityService.getCurrentUserId();
    Memoir memoir = memoirFacade.findById(memoirId);

    memoir.checkOwner(currentUserId);

    if(image != null){
      String imageUrl = memoir.getImageUrl();
      String[] splitImageUrl = imageUrl.split("/");
      String s3Key = splitImageUrl[splitImageUrl.length - 1];

      s3Service.deleteFile(s3Key);
      final String newImageUrl = s3Service.uploadFile(image);
      memoir.setImageUrl(newImageUrl);
    }

    if(memoirUpdateRequest != null){
      if(memoirUpdateRequest.getTitle() != null && !memoirUpdateRequest.getTitle().isEmpty()){
        memoir.setTitle(memoirUpdateRequest.getTitle());
      }

      if(memoirUpdateRequest.getContent() != null && !memoirUpdateRequest.getContent().isEmpty()){
        memoir.setContent(memoirUpdateRequest.getContent());
      }

      if(memoirUpdateRequest.getFeels() != null && !memoirUpdateRequest.getFeels().isEmpty()){
        memoir.setFeels(memoirUpdateRequest.getFeels());
      }
    }

    memoirRepository.save(memoir);

    return MemoirFindResponse.builder()
            .id(memoir.getId())
            .title(memoir.getTitle())
            .author(memoir.getAuthor().getNickname())
            .content(memoir.getContent())
            .feels(memoir.getFeels())
            .imageUrl(memoir.getImageUrl())
            .published(memoir.getPublished())
            .postDate(memoir.getPostDate())
            .build();
  }
}
