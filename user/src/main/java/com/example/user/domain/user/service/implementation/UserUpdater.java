package com.example.user.domain.user.service.implementation;

import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUpdater {

    private final UserRepository userRepository;

    public User update(User updatableUser, User user) {
        updatableUser.userUpdateBuilder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
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
