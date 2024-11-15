package com.memoir.domain.memoir.controller.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PublishedResponse {
    private boolean isPublished;
}
