package com.example.user.domain.auth.presentation.dto.res;

public record Token(
        String RefreshToken,
        String AccessToken
) {
}
