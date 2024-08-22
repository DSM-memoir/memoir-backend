package com.memoir.global.filter;

import com.memoir.global.security.jwt.JwtParser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FilterConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtParser jwtParser;

    @Override
    public void init(HttpSecurity builder) {}

    @Override
    public void configure(HttpSecurity builder) {
        builder.addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class);
        // TODO : 예외처리 필터 추가
    }
}
