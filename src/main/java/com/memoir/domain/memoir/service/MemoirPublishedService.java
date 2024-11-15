package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.controller.response.PublishedResponse;
import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.global.security.SecurityService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoirPublishedService {

    private final MemoirFacade memoirFacade;
    private final SecurityService securityService;
    private final MemoirRepository memoirRepository;

    public PublishedResponse execute(UUID memoirId) {
        UUID currentUserId = securityService.getCurrentUserId();
        Memoir memoir = memoirFacade.findById(memoirId);

        memoir.checkOwner(currentUserId);
        memoir.setPublished(!memoir.getPublished());

        memoirRepository.save(memoir);

        return PublishedResponse.builder()
                .isPublished(!memoir.getPublished())
                .build();
    }
}
