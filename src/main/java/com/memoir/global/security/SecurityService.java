package com.memoir.global.security;

import com.memoir.domain.user.entity.User;
import com.memoir.domain.user.exception.UserNotFoundException;
import com.memoir.domain.user.repository.UserRepository;
import com.memoir.global.security.principle.CustomUserDetails;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {
    
    private final UserRepository userRepository;

    public UUID getCurrentUserId() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
    }

    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId())
                .orElseThrow(UserNotFoundException::new);
    }

}
