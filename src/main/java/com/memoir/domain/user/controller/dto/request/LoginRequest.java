package com.memoir.domain.user.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginRequest {

    @NotNull
    private String accountId;

    @NotNull
    private String password;
}
