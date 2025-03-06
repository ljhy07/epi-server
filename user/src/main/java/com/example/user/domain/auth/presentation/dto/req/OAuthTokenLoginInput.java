package com.example.user.domain.auth.presentation.dto.req;

public record OAuthTokenLoginInput(
        String loginType,
        String accessToken
) {
}
