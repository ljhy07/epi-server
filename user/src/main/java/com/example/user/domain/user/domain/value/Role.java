package com.example.user.domain.user.domain.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public enum Role {
    Admin("ROLE_ADMIN"),
    Store("ROLE_STORE"),
    User("ROLE_USER");

    private final String role;
}
