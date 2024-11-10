package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.controller.request.MemoirCreateRequest;
import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.controller.response.MemoirDetailDTO;
import com.memoir.domain.memoir.service.MemoirCreateService;
import com.memoir.domain.memoir.service.MemoirDeleteService;
import com.memoir.domain.memoir.service.MemoirFindAllService;
import com.memoir.domain.memoir.service.MemoirFindByUUID;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/memoir")
@RequiredArgsConstructor
public class MemoirController {
  private final MemoirFindAllService memoirFindAll;
  private final MemoirCreateService memoirCreate;
  private final MemoirDeleteService memoirDeleteService;
  private final MemoirFindByUUID memoirFindByUUID;

  @GetMapping
  public MemoirFindAllResponse findAll(){
    return memoirFindAll.execute();
  }

  @GetMapping("/{memoir-id}")
  public MemoirDetailDTO findByUUID(@PathVariable("memoir-id") UUID memoirId){
    return memoirFindByUUID.execute(memoirId);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void create(
          @Valid @RequestPart(value = "data") MemoirCreateRequest request,
          @RequestPart(value = "image", required = false) MultipartFile image
          ) {
    memoirCreate.execute(request, image);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{memoir-id}")
  public void delete(@PathVariable("memoir-id") UUID memoirId) {
    memoirDeleteService.execute(memoirId);
  }
}
