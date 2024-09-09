package com.memoir.domain.user.repository;

import com.memoir.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByAccountIdAndPassword(String AccountId, String Password);

    boolean existsByAccountId(String AccountId);
}
