package com.example.user.domain.user.presentation.dto.req;

public record UserDeleteInput(
        Long id,
        String accessToken
) {
}
