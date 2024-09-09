package com.memoir.global.filter;

import com.memoir.global.security.jwt.JwtParser;
import com.memoir.global.security.jwt.JwtProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtParser jwtParser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = extractToken(request);

        if (token != null) {
            SecurityContextHolder.clearContext();
            SecurityContextHolder.getContext().setAuthentication(jwtParser.getAuthentication(token));
        }

        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.HEADER);
        if (token != null && token.startsWith(JwtProperties.PREFIX)) {
            return token.substring(JwtProperties.PREFIX.length());
        }
        return null;
    }
}
