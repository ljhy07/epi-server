package com.example.user.application.user.presentation.dto.res;

import com.example.user.application.user.domain.value.LoginType;
import com.example.user.application.user.domain.value.Role;

public record UserResponse(
        Long id,
        String name,
        String email,
        String phone,
        LoginType loginType,
        Role role
) {
}
