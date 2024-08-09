package com.memoir.domain.memoir.dto;

import jakarta.validation.constraints.NotNull;

public record MemoirRequest(
        @NotNull(message = "제목이 들어오지 않았습니다.")
        String title,
        @NotNull(message = "내용이 들어오지 않았습니다.")
        String content,
        @NotNull(message = "느낀점이 들어오지 않았습니다.")
        String feeling
) {
}
