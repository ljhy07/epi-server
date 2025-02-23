package com.example.user.domain.user.presentation.dto.req;

public record UserPasswordUpdateInput(
        Long userId,
        String password
) {
}
