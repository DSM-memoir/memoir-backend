package com.memoir.domain.memoir.service;

import com.memoir.domain.memoir.entity.Memoir;
import com.memoir.domain.memoir.repository.MemoirRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemoirFacade {

    private final MemoirRepository memoirRepository;

    public Memoir findById(UUID memoir_id) {
        return memoirRepository.findById(memoir_id)
                .orElseThrow(() -> new RuntimeException("Memoir not found"));
    }
}
