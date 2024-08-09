package com.memoir.domain.memoir.dto;


import com.memoir.domain.memoir.entity.MemoirEntity;

import java.time.LocalDateTime;

public record MemoirResponse(
        Long id,
        String title,
        String imagePath,
        String contents,
        String feeling,
        LocalDateTime createdAt
) {

  public static MemoirResponse of(MemoirEntity entity) {
    return new MemoirResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getImagePath(),
            entity.getContents(),
            entity.getFeeling(),
            entity.getCreateAt()
    );
  }
}
