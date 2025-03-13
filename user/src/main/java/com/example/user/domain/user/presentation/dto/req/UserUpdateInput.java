package com.example.user.domain.user.presentation.dto.req;

public record UserUpdateInput(
        String accessToken,
        String name,
        String email,
        String phone,
        String Role
) {
}
