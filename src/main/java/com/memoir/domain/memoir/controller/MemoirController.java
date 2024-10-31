package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.controller.request.MemoirCreateRequest;
import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.service.MemoirCreateService;
import com.memoir.domain.memoir.service.MemoirFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/memoir")
@RequiredArgsConstructor
public class MemoirController {
  private final MemoirFindAllService memoirFindAll;
  private final MemoirCreateService memoirCreate;

  @GetMapping
  public MemoirFindAllResponse findAll(){
    return memoirFindAll.execute();
  }

  @PostMapping
  public void create(
          @RequestPart(value = "data") MemoirCreateRequest request,
          @RequestPart(value = "image") MultipartFile image
          ) throws IOException {
    memoirCreate.execute(request, image);
  }
}
