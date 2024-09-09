package com.memoir.domain.user.controller.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String accountId;
    private String password;
}
