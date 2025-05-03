package com.example.user.application.auth.service.implementation.google;

import com.example.user.global.config.properties.auth.GoogleProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoogleAuthLinkService {
    private static final String QUERY_STRING = "?client_id=%s&redirect_uri=%s" +
            "&response_type=token&scope=https://www.googleapis.com/auth/userinfo.email";
    private final GoogleProperties googleProperties;

    public String execute() {
        return googleProperties.getBaseUrl() +
                String.format(
                        QUERY_STRING,
                        googleProperties.getClientId(),
                        googleProperties.getRedirectUrl()
                );
    }
}
