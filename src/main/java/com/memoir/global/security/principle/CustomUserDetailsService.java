package com.memoir.global.security.principle;

import com.memoir.domain.user.exception.UserNotFoundException;
import com.memoir.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findById(UUID.fromString(username))
                .map(user -> CustomUserDetails.builder().userId(user.getId()).build())
                .orElseThrow(UserNotFoundException::new);
    }
}
