package com.example.user.application.auth.service.implementation;

import com.example.user.application.auth.exception.InvalidCredentialsException;
import com.example.user.application.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthValidator {

    private final PasswordEncoder passwordEncoder;

    public void validatePassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }
    }

    // 비밀번호 해싱
    public String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
