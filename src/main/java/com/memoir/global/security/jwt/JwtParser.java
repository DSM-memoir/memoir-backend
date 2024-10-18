package com.memoir.global.security.jwt;

import com.memoir.global.security.SecurityProperties;
import com.memoir.global.security.principle.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import java.util.Optional;
import java.util.OptionalInt;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final SecurityProperties properties;

    private final CustomUserDetailsService userDetailsService;

    public Authentication getAuthentication(String token) {
        Jws<Claims> claims = getClaims(token);

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getBody().getId());


        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private Jws<Claims> getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(properties.secretKey)
                .build()
                .parseClaimsJws(token);
    }
}
