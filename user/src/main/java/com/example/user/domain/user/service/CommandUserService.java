package com.example.user.domain.user.service;

import com.example.user.domain.user.domain.User;
import com.example.user.domain.user.presentation.dto.req.UserCreateInput;
import com.example.user.domain.user.presentation.dto.req.UserDeleteInput;
import com.example.user.domain.user.presentation.dto.req.UserPasswordUpdateInput;
import com.example.user.domain.user.presentation.dto.req.UserUpdateInput;
import com.example.user.domain.user.presentation.dto.res.UserCommandResponse;
import com.example.user.domain.user.service.implementation.UserCreator;
import com.example.user.domain.user.service.implementation.UserDeleter;
import com.example.user.domain.user.service.implementation.UserReader;
import com.example.user.domain.user.service.implementation.UserUpdater;
import com.example.user.global.security.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommandUserService {

    private final UserCreator userCreator;
    private final UserUpdater userUpdater;
    private final UserDeleter userDeleter;
    private final UserReader userReader;
    private final JwtUtil jwtUtil;

    public UserCommandResponse save(
            UserCreateInput userCreateInput
    ){
        User user = userCreator.create(userCreateInput.toEntity());

        return new UserCommandResponse(
                user.getId()
        );
    }

    public UserCommandResponse update(
            String accessToken,
            UserUpdateInput userUpdateInput
    ){
        User updatableUser = userReader.findById(jwtUtil.getId(accessToken));
        User user = userUpdater.update(updatableUser, userUpdateInput);

        return new UserCommandResponse(
                user.getId()
        );
    }

    public UserCommandResponse updatePassword(
            String accessToken,
            UserPasswordUpdateInput userPasswordUpdateInput
    ){
        User updatableUser = userReader.findById(jwtUtil.getId(accessToken));
        User user = userUpdater.updatePassword(updatableUser, userPasswordUpdateInput.password());

        return new UserCommandResponse(
                user.getId()
        );
    }

    public UserCommandResponse delete(
            String accessToken
    ){
        User updatableUser = userReader.findById(jwtUtil.getId(accessToken));
        User user = userDeleter.delete(updatableUser);

        return new UserCommandResponse(
                user.getId()
        );
    }

}
