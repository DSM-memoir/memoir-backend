package com.memoir.domain.memoir.repository;

import com.memoir.domain.memoir.entity.Memoir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemoirRepository extends JpaRepository<Memoir, UUID> {
}
