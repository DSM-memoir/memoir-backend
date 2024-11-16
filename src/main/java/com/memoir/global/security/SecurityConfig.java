package com.memoir.global.security;

import com.memoir.global.filter.FilterConfig;
import com.memoir.global.filter.JwtFilter;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final FilterConfig filterConfig;

    @Bean
    protected DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(req -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:5173"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    return config;
                }))
                .formLogin(AbstractHttpConfigurer::disable)
                .headers(headers -> headers.frameOptions(it -> it.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http
                .authorizeHttpRequests( authorize -> authorize
                        .requestMatchers("/").permitAll()

                        .requestMatchers("/user/login", "/user/signup", "/error").permitAll()

                        .requestMatchers(HttpMethod.GET, "/user").authenticated()

                        .requestMatchers(HttpMethod.GET, "/memoir").authenticated()
                        .requestMatchers(HttpMethod.POST, "/memoir").authenticated()
                        .requestMatchers(HttpMethod.GET, "/memoir/{memoir-id}").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/memoir/{memoir-id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/memoir/{memoir-id}").authenticated()

                        .requestMatchers(HttpMethod.PATCH, "/memoir/like/{memoir-id}").authenticated()
                        .requestMatchers(HttpMethod.PATCH, "/memoir/published/{memoir-id}").authenticated()

                        .anyRequest().denyAll()
                );
        http
                .apply(filterConfig);
        http
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    protected BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
