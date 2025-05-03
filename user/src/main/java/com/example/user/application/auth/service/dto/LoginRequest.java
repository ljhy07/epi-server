package com.example.user.application.auth.service.dto;

public record LoginRequest(
        String email,
        String password
) {
}
