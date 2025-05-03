package com.example.user.application.auth.presentation.dto.req;

public record OAuthTokenLoginInput(
        String loginType,
        String accessToken
) {
}
