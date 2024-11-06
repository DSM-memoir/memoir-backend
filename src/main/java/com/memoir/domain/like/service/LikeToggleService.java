package com.memoir.domain.like.service;

import com.memoir.domain.like.controller.dto.response.LikeResponse;
import com.memoir.domain.like.entity.Like;
import com.memoir.domain.like.repository.LikeRepository;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.service.MemoirFacade;
import com.memoir.domain.user.entity.User;
import com.memoir.global.security.SecurityService;
import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeToggleService {

    private final SecurityService securityService;
    private final LikeRepository likeRepository;
    private final MemoirFacade memoirFacade;

    public LikeResponse execute(UUID memoir_id) {
        System.out.println("로직 시작");
        User user = securityService.getCurrentUser();
        Memoir memoir = memoirFacade.findById(memoir_id);

        Like like = Like.builder()
                .user(user)
                .memoir(memoir)
                .build();

        boolean isLike = likeRepository.existsByUserIdAndMemoirId(user.getId(), memoir.getId());

        if (isLike) likeRepository.deleteByUserIdAndMemoirId(user.getId(), memoir.getId());
        else likeRepository.save(like);

        return LikeResponse.builder()
                .isLike(!isLike)
                .build();
    }
}
