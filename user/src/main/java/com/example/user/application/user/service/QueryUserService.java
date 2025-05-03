package com.example.user.application.user.service;

import com.example.user.application.user.domain.User;
import com.example.user.application.user.presentation.dto.res.UserResponse;
import com.example.user.application.user.service.implementation.UserReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryUserService {

    private final UserReader userReader;

    public List<UserResponse> getUsers(){
        List<User> users = userReader.findAll();
        List<UserResponse> userQueryResponses = new ArrayList<>();

        for (User user : users) {
            userQueryResponses.add(
                    new UserResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail(),
                            user.getPhone(),
                            user.getLoginType(),
                            user.getRole()
                    )
            );
        }

        return  userQueryResponses;
    }

    public UserResponse getUser(
            Long userId
    ){
        User user = userReader.findById(userId);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }

    public UserResponse getUserByAccessToken(){
        User user = userReader.findByEmailFromSecurity();

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }

    public UserResponse getUserByEmail(
            String email
    ){
        User user = userReader.findByEmail(email);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }
}
