package com.example.user.application.auth.presentation.dto.req;

public record LoginInput(
        String email,
        String password
) {
}
