package com.memoir.domain.user.service;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import com.memoir.domain.user.controller.dto.response.MyPageResponse;
import com.memoir.domain.user.entity.User;
import com.memoir.domain.user.repository.UserRepository;
import com.memoir.global.security.SecurityService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyPageService {

    private final SecurityService securityService;
    private final MemoirRepository memoirRepository;

    public MyPageResponse execute() {
        User user = securityService.getCurrentUser();

        List<Memoir> memoirs = memoirRepository.findAllByAuthorId(user.getId());

        return MyPageResponse.of(
                user.getNickname(),
                memoirs
        );
    }
}
