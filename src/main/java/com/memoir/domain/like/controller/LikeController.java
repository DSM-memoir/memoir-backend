package com.memoir.domain.like.controller;

import com.memoir.domain.like.controller.dto.response.LikeResponse;
import com.memoir.domain.like.service.LikeToggleService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/memoir/like")
@RestController
@RequiredArgsConstructor
public class LikeController {

    private final LikeToggleService likeToggleService;

    @PatchMapping("/{memoir_id}")
    private LikeResponse likeToggle(@PathVariable UUID memoir_id) {
        return likeToggleService.execute(memoir_id);
    }
}
