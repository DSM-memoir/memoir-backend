package com.memoir.domain.user.controller;

import com.memoir.domain.user.controller.dto.request.LoginRequest;
import com.memoir.domain.user.controller.dto.request.SignUpRequest;
import com.memoir.domain.user.controller.dto.response.LoginResponse;
import com.memoir.domain.user.service.LoginService;
import com.memoir.domain.user.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;
    private final SignUpService signUpService;

    @PostMapping("/login")
    private LoginResponse signIn(@RequestBody LoginRequest loginRequest) {
        return loginService.execute(loginRequest);
    }

    @PostMapping("/signup")
    private LoginResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.execute(signUpRequest);
    }

}
