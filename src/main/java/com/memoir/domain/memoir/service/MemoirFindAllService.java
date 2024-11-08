package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.controller.response.MemoirFindResponse;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirFindAllService {
  private final MemoirRepository memoirRepository;
  private final SecurityService securityService;

  public MemoirFindAllResponse execute() {
    UUID userId = securityService.getCurrentUserId();

    final List<Memoir> memoirs = memoirRepository.findPostList(userId);

    final List<MemoirFindResponse> memoirFindResponseList = memoirs
            .stream()
            .map((x ->
                    MemoirFindResponse.builder()
                            .id(x.getId())
                            .title(x.getTitle())
                            .author(x.getAuthor().getNickname())
                            .content(x.getContent())
                            .imageUrl(x.getImageUrl())
                            .published(x.getPublished())
                            .postDate(x.getPostDate())
                            .build()
            ))
            .toList();

    return MemoirFindAllResponse.builder().memoirs(memoirFindResponseList).build();
  }
}
