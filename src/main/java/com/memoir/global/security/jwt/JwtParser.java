package com.memoir.global.security.jwt;

import com.memoir.global.security.SecurityProperties;
import com.memoir.global.security.exception.ExpiredTokenException;
import com.memoir.global.security.exception.InvalidTokenException;
import com.memoir.global.security.principle.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
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
        try {
            return Jwts.parser()
                    .setSigningKey(properties.secretKey)
                    .build()
                    .parseClaimsJws(token);
        }catch (Exception e) {
            if (e instanceof ExpiredJwtException) throw new ExpiredTokenException();
            else if (e instanceof SignatureException) throw new InvalidTokenException();
            else throw e;
        }
    }
}
