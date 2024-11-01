package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.controller.response.MemoirFindResponse;
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
