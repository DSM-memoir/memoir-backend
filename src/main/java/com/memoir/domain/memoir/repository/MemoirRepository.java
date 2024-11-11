package com.memoir.domain.memoir.repository;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.controller.response.MemoirDetailDTO;
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

    @Query(value = """
            select new com.memoir.domain.memoir.controller.response.MemoirDetailDTO(m.id, m.title, m.author.nickname, m.content, m.feels, m.postDate, count(lt.memoir), (case when :userId = lt.user.id then true else false end), m.imageUrl, m.published) from Memoir as m
            left join like_table lt on m.id = lt.memoir.id
            where m.id = :memoirId
            group by m.id
            """)
    MemoirDetailDTO findDetailById(@Param("memoirId") UUID memoirId, @Param("userId") UUID userId);
}
