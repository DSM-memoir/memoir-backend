package com.memoir.domain.like.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter @Setter
public class LikeResponse {

    private Boolean isLike;
}
