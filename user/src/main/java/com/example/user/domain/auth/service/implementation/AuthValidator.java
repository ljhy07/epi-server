package com.example.user.domain.auth.service.implementation;

import com.example.user.domain.auth.exception.InvalidCredentialsException;
import com.example.user.domain.user.domain.User;
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
}
