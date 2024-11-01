package com.memoir.domain.user.service;

import com.memoir.domain.user.entity.User;
import com.memoir.domain.user.exception.PasswordNotMatch;
import com.memoir.domain.user.exception.UserAccountIdAlreadyExistsException;
import com.memoir.domain.user.exception.UserNotFoundException;
import com.memoir.domain.user.repository.UserRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User findByAccountIdAndPassword(String accountId, String password) {
        Optional<User> ouser = userRepository.findByAccountId(accountId);

        User user = ouser.orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) throw new PasswordNotMatch();

        return user;
    }

    public User findById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {
        if (userRepository.existsByAccountId(user.getAccountId())) throw new UserAccountIdAlreadyExistsException();

        return userRepository.save(user);
    }
}
