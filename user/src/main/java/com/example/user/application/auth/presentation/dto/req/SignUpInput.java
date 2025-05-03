package com.example.user.application.auth.presentation.dto.req;

import com.example.user.application.user.domain.value.Role;

public record SignUpInput(
        String name,
        String email,
        String password,
        String phone,
        Role role
) {
}
