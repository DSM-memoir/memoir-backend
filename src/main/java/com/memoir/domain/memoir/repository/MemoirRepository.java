package com.memoir.domain.memoir.repository;

import com.memoir.domain.memoir.entity.MemoirEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoirRepository extends JpaRepository<MemoirEntity, Long> {
}
