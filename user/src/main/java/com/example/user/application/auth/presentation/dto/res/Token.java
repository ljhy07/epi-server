package com.example.user.application.auth.presentation.dto.res;

public record Token(
        String refreshToken,
        String accessToken
) {
}
