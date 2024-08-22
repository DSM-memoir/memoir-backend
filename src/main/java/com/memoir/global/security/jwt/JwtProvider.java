package com.memoir.global.security.jwt;

import com.memoir.global.security.SecurityProperties;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final SecurityProperties properties;

    public void receiveToken(UUID userId) {

    }

    public String generateAccessToken(UUID userid) {
        return Jwts.builder()
                .signWith(properties.secretKey, SignatureAlgorithm.HS256)
                .setHeaderParam(Header.JWT_TYPE, JwtProperties.ACCESS)
                .setId(userid.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + properties.accessExp * 1000L))
                .compact();
    }
}
