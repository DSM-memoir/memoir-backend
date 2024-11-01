package com.memoir.domain.memoir.repository;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.user.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemoirRepository extends JpaRepository<Memoir, UUID> {

    List<Memoir> findAllByAuthorId(UUID author);
}
