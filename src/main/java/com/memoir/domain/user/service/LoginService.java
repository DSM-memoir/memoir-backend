package com.memoir.domain.user.service;

import com.memoir.domain.user.controller.dto.request.LoginRequest;
import com.memoir.domain.user.controller.dto.response.LoginResponse;
import com.memoir.domain.user.entity.User;
import com.memoir.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;

    public LoginResponse execute(LoginRequest loginRequest) {

        User user = userFacade.findByAccountIdAndPassword(
                loginRequest.getAccountId(),
                loginRequest.getPassword()
        );

        return LoginResponse.builder()
                .token(jwtProvider.generateAccessToken(user.getId()))
                .build();
    }
}
