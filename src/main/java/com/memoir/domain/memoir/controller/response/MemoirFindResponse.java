package com.memoir.domain.memoir.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
public class MemoirFindResponse {
  final UUID id;
  final String title;
  final String author;
  final String content;
  final String imageUrl;
  final boolean published;
  final LocalDate postDate;
}