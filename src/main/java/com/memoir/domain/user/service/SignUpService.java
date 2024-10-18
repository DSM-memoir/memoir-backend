package com.memoir.domain.user.service;

import com.memoir.domain.user.controller.dto.request.SignUpRequest;
import com.memoir.domain.user.controller.dto.response.LoginResponse;
import com.memoir.domain.user.entity.User;
import com.memoir.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SignUpService {

    private final UserFacade userFacade;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public LoginResponse execute(SignUpRequest request) {

        User user = User.builder()
                .accountId(request.getAccountId())
                .nickname(request.getNickname())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User saveuser = userFacade.save(user);

        return LoginResponse.builder()
                .token(jwtProvider.generateAccessToken(saveuser.getId()))
                .build();
    }

}
