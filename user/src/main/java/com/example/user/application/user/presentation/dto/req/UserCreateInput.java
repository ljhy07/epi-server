package com.example.user.application.user.presentation.dto.req;

import com.example.user.application.user.domain.value.Role;
import lombok.Builder;

@Builder
public record UserCreateInput (
        String name,
        String password,
        String email,
        String phone,
        Role role
){
}
