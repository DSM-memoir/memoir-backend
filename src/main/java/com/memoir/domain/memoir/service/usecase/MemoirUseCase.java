package com.memoir.domain.memoir.service.usecase;

import com.memoir.domain.memoir.dto.MemoirRequest;
import com.memoir.domain.memoir.dto.MemoirResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MemoirUseCase {
  void create(MemoirRequest memoirRequest, MultipartFile imageFile) throws IOException;

  void delete(Long id);

  List<MemoirResponse> getAll();
}
