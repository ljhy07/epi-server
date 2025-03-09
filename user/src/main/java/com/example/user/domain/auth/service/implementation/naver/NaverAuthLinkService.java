package com.example.user.domain.auth.service.implementation.naver;

import com.example.user.global.config.properties.AuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverAuthLinkService {
    private static final String QUERY_STRING = "?response_type=code&" +
            "client_id=%s&redirect_uri=%s";
    private final AuthProperties authProperties;

    public String execute() {
        return authProperties.getNaverBaseUrl() +
                String.format(
                        QUERY_STRING,
                        authProperties.getNaverClientId(),
                        authProperties.getNaverRedirectUrl()
                );
    }
}
