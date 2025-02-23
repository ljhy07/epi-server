package com.example.user.domain.user.presentation.dto.res;

public record UserQueryResponse(
        Long id,
        String name,
        String email
) {
}
