package com.example.user.domain.user.presentation.dto.req;

public record UserPasswordUpdateInput(
        String accessToken,
        String password
) {
}
