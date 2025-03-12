package com.example.user.domain.auth.service.implementation;

import com.example.user.domain.auth.domain.Refresh;
import com.example.user.domain.auth.domain.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthReader {

    private final RefreshRepository refreshRepository;

    public Refresh findByRefreshToken(String refreshToken) {
        return refreshRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));
    }

    public Refresh findByUserId(Long userId) {
        return refreshRepository.findByUserId(userId);
    }
}
