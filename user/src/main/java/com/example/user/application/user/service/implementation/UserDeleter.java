package com.example.user.application.user.service.implementation;

import com.example.user.application.user.domain.User;
import com.example.user.application.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDeleter {

    private final UserRepository userRepository;

    public User delete(User user){
        userRepository.delete(user);
        return user;
    }

}
