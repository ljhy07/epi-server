package com.example.user.application.user.service.implementation;

import com.example.user.application.user.domain.User;
import com.example.user.application.user.domain.repository.UserRepository;
import com.example.user.application.user.domain.value.Role;
import com.example.user.application.user.presentation.dto.req.UserUpdateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public User update(User updatableUser, UserUpdateInput user) {
        Role role = switch (user.Role()) {
            case "Store" -> Role.Store;
            case "User" -> Role.User;
            default -> null;
        };

        updatableUser.userUpdateBuilder()
                .name(user.name())
                .email(user.email())
                .role(role)
                .build();

        return userRepository.save(updatableUser);
    }

    public User updatePassword(User updatableUser, String newPassword) {
        updatableUser.userPasswordUpdateBuilder()
                .password(newPassword)
                .build();

        return userRepository.save(updatableUser);
    }
}
