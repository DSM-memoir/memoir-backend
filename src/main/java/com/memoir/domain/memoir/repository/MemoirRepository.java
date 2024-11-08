package com.memoir.domain.memoir.repository;

import com.memoir.domain.memoir.entity.Memoir;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemoirRepository extends JpaRepository<Memoir, UUID> {

    List<Memoir> findAllByAuthorId(UUID author);

    @Query(value = """
            SELECT * FROM memoir WHERE published = true
            UNION ALL
            SELECT * FROM memoir WHERE author = :userId AND published = false
            """, nativeQuery = true)
    List<Memoir> findPostList(@Param("userId") UUID userId);
}
