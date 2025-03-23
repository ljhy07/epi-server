package com.example.user.domain.user.service.implementation;

import com.example.user.domain.auth.presentation.dto.req.AdditionalInfoInput;
import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreator {

    private final UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
    }

    public User additionalInfo(User user, AdditionalInfoInput additionalInfoInput){
        user.additionalInfoBuilder()
                .name(additionalInfoInput.name())
                .phone(additionalInfoInput.phone())
                .build();

        return userRepository.save(user);
    }
}
