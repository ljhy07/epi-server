package com.example.user.domain.auth.presentation.dto.req;

import com.example.user.domain.user.domain.value.Role;

public record SignUpInput(
        String name,
        String email,
        String password,
        String phone,
        Role role
) {
}
