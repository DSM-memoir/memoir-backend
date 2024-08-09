package com.memoir.domain.memoir.controller;

import com.memoir.domain.memoir.dto.MemoirRequest;
import com.memoir.domain.memoir.dto.MemoirResponse;
import com.memoir.domain.memoir.service.usecase.MemoirUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// spring bean

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/memoir")
public class MemoirController {
  private final MemoirUseCase useCase;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@Valid @RequestPart(name = "request") MemoirRequest request, @RequestPart(name = "file") MultipartFile imageFile) throws IOException {
    useCase.create(request, imageFile);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable(name = "id") Long id) {
    useCase.delete(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<MemoirResponse> getAll() {
    return useCase.getAll();
  }
}
