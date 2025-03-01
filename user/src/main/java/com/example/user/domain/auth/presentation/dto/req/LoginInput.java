package com.example.user.domain.auth.presentation.dto.req;

public record LoginInput(
        String email,
        String password
) {
}
