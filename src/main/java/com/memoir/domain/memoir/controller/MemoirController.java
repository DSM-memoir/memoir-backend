package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.controller.request.MemoirCreateRequest;
import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.service.MemoirCreateService;
import com.memoir.domain.memoir.service.MemoirDeleteService;
import com.memoir.domain.memoir.service.MemoirFindAllService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/memoir")
@RequiredArgsConstructor
public class MemoirController {
  private final MemoirFindAllService memoirFindAll;
  private final MemoirCreateService memoirCreate;
  private final MemoirDeleteService memoirDeleteService;

  @GetMapping
  public MemoirFindAllResponse findAll(){
    return memoirFindAll.execute();
  }

  @PostMapping
  public void create(
          @Valid @RequestPart(value = "data") MemoirCreateRequest request,
          @RequestPart(value = "image", required = false) MultipartFile image
          ) {
    memoirCreate.execute(request, image);
  }

  @DeleteMapping("/{memoir-id}")
  public void delete(@PathVariable("memoir-id") UUID memoirId) throws IOException {
    memoirDeleteService.execute(memoirId);
  }
}
