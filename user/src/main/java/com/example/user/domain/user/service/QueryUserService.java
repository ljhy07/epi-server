package com.example.user.domain.user.service;

import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.presentation.dto.res.UserQueryResponse;
import com.example.user.domain.user.service.implementation.UserReader;
import com.example.user.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryUserService {

    private final UserReader userReader;
    private final JwtUtil jwtUtil;

    public List<UserQueryResponse> getUsers(){
        List<User> users = userReader.findAll();
        List<UserQueryResponse> userQueryResponses = new ArrayList<>();

        for (User user : users) {
            userQueryResponses.add(
                    new UserQueryResponse(
                            user.getId(),
                            user.getName(),
                            user.getEmail()
                    )
            );
        }

        return  userQueryResponses;
    }

    public UserQueryResponse getUser(
            Long userId
    ){
        User user = userReader.findById(userId);

        return new UserQueryResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public UserQueryResponse getUserByAccessToken(
            String accessToken
    ){
        User user = userReader.findById(jwtUtil.getId(accessToken));

        return new UserQueryResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public UserQueryResponse getUserByEmail(
            String email
    ){
        User user = userReader.findByEmail(email);

        return new UserQueryResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
