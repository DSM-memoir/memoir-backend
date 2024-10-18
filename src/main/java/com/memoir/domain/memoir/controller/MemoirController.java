package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.controller.response.MemoirFindAllResponse;
import com.memoir.domain.memoir.service.MemoirFindAllService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memoir")
@RequiredArgsConstructor
public class MemoirController {
  private final MemoirFindAllService memoirFindAll;

  @GetMapping
  public MemoirFindAllResponse findAll(){
    return memoirFindAll.execute();
  }
}
