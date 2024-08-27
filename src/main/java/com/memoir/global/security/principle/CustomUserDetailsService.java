package com.memoir.global.security.principle;

import com.memoir.domain.user.entity.User;
import com.memoir.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(UUID.fromString(username))
                .map(user -> CustomUserDetails.builder().userId(user.getId()).build())
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
    }
}
