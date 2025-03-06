package com.example.user.domain.auth.presentation.dto.req;

public record OAuthCodeLoginInput(
        String loginType,
        String code
) {
}
