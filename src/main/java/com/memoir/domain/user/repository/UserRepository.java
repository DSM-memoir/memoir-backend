package com.memoir.domain.user.repository;

import com.memoir.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByAccountId(String AccountId);

    boolean existsByAccountId(String AccountId);
}
