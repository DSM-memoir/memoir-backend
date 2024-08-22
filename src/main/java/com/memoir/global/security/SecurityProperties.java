package com.memoir.global.security;

import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@ConfigurationProperties(prefix = "secret")
public class SecurityProperties {
    public final SecretKey secretKey;
    public final int accessExp;
    public final int refreshExp;

    public SecurityProperties(String secretKey, int accessExp, int refreshExp) {
        this.secretKey = Keys.hmacShaKeyFor(
                Base64.getEncoder().encodeToString(secretKey.getBytes())
                        .getBytes(StandardCharsets.UTF_8));
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
    }
}
