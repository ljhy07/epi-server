package com.example.user.domain.auth.service.dto;

public record LoginRequest(
        String email,
        String password
) {
}
