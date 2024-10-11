package com.memoir.domain.user.service;

import com.memoir.domain.user.entity.User;
import com.memoir.domain.user.exception.UserAccountIdAlreadyExistsException;
import com.memoir.domain.user.exception.UserNotFoundException;
import com.memoir.domain.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User findByAccountIdAndPassword(String accountId, String password) {
        Optional<User> user = userRepository.findByAccountIdAndPassword(accountId, password);

        return user.orElseThrow(UserNotFoundException::new);
    }

    public User save(User user) {
        if (userRepository.existsByAccountId(user.getAccountId())) throw new UserAccountIdAlreadyExistsException();

        return userRepository.save(user);
    }
}
