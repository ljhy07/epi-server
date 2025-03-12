package com.example.user.domain.auth.service.implementation.kakao;

import com.example.user.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoAuthLinkService {
    private static final String QUERY_STRING = "?client_id=%s&" +
            "redirect_uri=%s&response_type=code";
    private final AuthProperties authProperties;

    public String execute() {
        return authProperties.getKakaoBaseUrl() +
                String.format(
                        QUERY_STRING,
                        authProperties.getKakaoSecretId(),
                        authProperties.getKakaoRedirectUrl()
                );
    }
}
