package com.memoir.domain.user.controller.dto.request;

import lombok.Getter;

@Getter
public class SignUpRequest {
    private String accountId;
    private String nickname;
    private String password;
}
