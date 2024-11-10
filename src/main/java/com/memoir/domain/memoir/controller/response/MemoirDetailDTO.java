package com.memoir.domain.memoir.controller.response;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MemoirDetailDTO {
    private UUID id;
    private String title;
    private String author;
    private String content;
    private String feels;
    private LocalDate post_date;
    private long likes;
    private boolean isLiked;
    private String image_url;
    private boolean published;
}
