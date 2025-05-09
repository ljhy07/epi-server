package com.example.user.application.auth.service.implementation;

import com.example.user.application.auth.domain.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthDeleter {

    private final RefreshRepository refreshRepository;

    public void deleteByRefresh(String refreshToken) {
        refreshRepository.deleteByRefreshToken(refreshToken);
    }

}
