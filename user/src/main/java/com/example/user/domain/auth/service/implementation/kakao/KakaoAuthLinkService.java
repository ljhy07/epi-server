package com.example.user.domain.auth.service.implementation.kakao;

import com.example.user.global.config.properties.auth.KakaoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KakaoAuthLinkService {
    private static final String QUERY_STRING = "&client_id=%s&" +
            "redirect_uri=%s&response_type=code";
    private final KakaoProperties kakaoProperties;

    public String execute() {
        return kakaoProperties.getBaseUrl() +
                String.format(
                        QUERY_STRING,
                        kakaoProperties.getClientId(),
                        kakaoProperties.getRedirectUrl()
                );
    }
}
