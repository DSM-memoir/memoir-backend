package com.memoir.domain.user.controller;

import com.memoir.domain.user.controller.dto.request.LoginRequest;
import com.memoir.domain.user.controller.dto.request.SignUpRequest;
import com.memoir.domain.user.controller.dto.response.LoginResponse;
import com.memoir.domain.user.controller.dto.response.MyPageResponse;
import com.memoir.domain.user.service.LoginService;
import com.memoir.domain.user.service.MyPageService;
import com.memoir.domain.user.service.SignUpService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final LoginService loginService;
    private final SignUpService signUpService;
    private final MyPageService myPageService;

    @PostMapping("/login")
    private LoginResponse signIn(@RequestBody @Valid LoginRequest loginRequest) {
        return loginService.execute(loginRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    private LoginResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return signUpService.execute(signUpRequest);
    }

    @GetMapping
    private MyPageResponse myPage() {
        return myPageService.execute();
    }
}
