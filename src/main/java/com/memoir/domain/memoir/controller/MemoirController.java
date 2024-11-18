package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.controller.request.MemoirCreateRequest;
import com.memoir.domain.memoir.controller.request.MemoirUpdateRequest;
import com.memoir.domain.memoir.controller.response.MemoirDetailDTO;
import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.controller.response.MemoirFindResponse;
import com.memoir.domain.memoir.controller.response.PublishedResponse;
import com.memoir.domain.memoir.service.*;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/memoir")
@RequiredArgsConstructor
public class MemoirController {
  private final MemoirFindAllService memoirFindAll;
  private final MemoirCreateService memoirCreate;
  private final MemoirDeleteService memoirDeleteService;
  private final MemoirFindByUUID memoirFindByUUID;
  private final MemoirPublishedService memoirPublishedService;
  private final MemoirUpdateService memoirUpdateService;

  @GetMapping
  public MemoirFindAllResponse findAll(@RequestParam(value = "sort", required = false, defaultValue = "RECENT") SortBy sortBy){
    return memoirFindAll.execute(sortBy.name());
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

  @ResponseStatus(HttpStatus.OK)
  @PatchMapping("/{memoir-id}")
  public MemoirFindResponse update(
          @PathVariable("memoir-id") UUID memoirId,
          @RequestPart(value = "data", required = false) MemoirUpdateRequest request,
          @RequestPart(value = "image", required = false) MultipartFile image
  ) {
    return memoirUpdateService.execute(memoirId, request, image);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{memoir-id}")
  public void delete(@PathVariable("memoir-id") UUID memoirId) {
    memoirDeleteService.execute(memoirId);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PatchMapping("/published/{memoir_id}")
  private PublishedResponse publishedToggle(@PathVariable UUID memoir_id) {
    return memoirPublishedService.execute(memoir_id);
  }
}

enum SortBy {
  LIKE,
  RECENT;
}