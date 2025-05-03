package com.example.user.application.auth.service.implementation;

import com.example.user.application.auth.domain.Refresh;
import com.example.user.application.auth.domain.repository.RefreshRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthCreator {

    private final RefreshRepository refreshRepository;

    public void createRefreshToken(Refresh refresh) {
        refreshRepository.save(refresh);
    }

}
