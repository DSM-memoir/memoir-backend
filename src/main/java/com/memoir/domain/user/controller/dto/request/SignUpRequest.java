package com.memoir.domain.user.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotNull
    private String accountId;

    @NotNull
    private String nickname;

    @NotNull
    private String password;
}
