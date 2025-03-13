package com.example.user.domain.user.presentation.dto.req;

import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.value.Role;
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
