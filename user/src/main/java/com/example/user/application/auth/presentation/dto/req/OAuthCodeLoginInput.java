package com.example.user.application.auth.presentation.dto.req;

public record OAuthCodeLoginInput(
        String loginType,
        String code
) {
}
