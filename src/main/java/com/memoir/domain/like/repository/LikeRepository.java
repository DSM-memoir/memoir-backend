package com.memoir.domain.like.repository;

import com.memoir.domain.like.entity.Like;
import com.memoir.domain.like.entity.LikeId;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, LikeId> {

    Boolean existsByUserIdAndMemoirId(UUID userId, UUID memoirId);

    void deleteByUserIdAndMemoirId(UUID userId, UUID memoirId);
}
