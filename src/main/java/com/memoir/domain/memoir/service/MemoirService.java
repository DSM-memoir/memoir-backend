package com.memoir.domain.memoir.service;
import com.memoir.domain.memoir.dto.MemoirRequest;
import com.memoir.domain.memoir.dto.MemoirResponse;
import com.memoir.domain.memoir.entity.MemoirEntity;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.domain.memoir.service.component.ImageComponent;
import com.memoir.domain.memoir.service.usecase.MemoirUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoirService implements MemoirUseCase {
  private final MemoirRepository memoirRepository;
  private final ImageComponent imageComponent;

  @Override
  public void create(MemoirRequest memoirRequest, MultipartFile imageFile) throws IOException {
    MemoirEntity memoirEntity = MemoirEntity.builder()
            .title(memoirRequest.title())
            .imagePath(imageComponent.storeFile(imageFile))
            .contents(memoirRequest.content())
            .feeling(memoirRequest.feeling())
            .build();

    memoirRepository.save(memoirEntity);
  }

  @Override
  public void delete(Long id) {
    memoirRepository.deleteById(id);
  }

  @Override
  public List<MemoirResponse> getAll() {
    List<MemoirEntity> findAll = memoirRepository.findAll();

    return findAll.stream().map(MemoirResponse::of).toList();
  }
}