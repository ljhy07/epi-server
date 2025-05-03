package com.example.user.application.user.domain.value;

import lombok.Getter;

@Getter
public enum Role {
    Admin("ROLE_ADMIN"),
    Store("ROLE_STORE"),
    User("ROLE_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static Role fromValue(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value) || role.getRole().equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role value: " + value);
    }
}
