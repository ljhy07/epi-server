package com.example.user.application.user.presentation.dto.req;

public record UserPasswordUpdateInput(
        String accessToken,
        String password
) {
}
