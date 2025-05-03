package com.example.user.application.auth.service.implementation.naver;

import com.example.user.global.config.properties.auth.NaverProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NaverAuthLinkService {
    private static final String QUERY_STRING = "?response_type=code&" +
            "client_id=%s&redirect_uri=%s";
    private final NaverProperties naverProperties;

    public String execute() {
        return naverProperties.getBaseUrl() +
                String.format(
                        QUERY_STRING,
                        naverProperties.getClientId(),
                        naverProperties.getRedirectUrl()
                );
    }
}
