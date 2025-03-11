package com.example.user.domain.auth.service;

import com.example.user.domain.auth.service.dto.CustomUserDetails;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.repository.UserRepository;
import com.example.user.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmail(email);

        return user.map(CustomUserDetails::new)
                .orElseThrow(UserNotFoundException::new);
    }
}
