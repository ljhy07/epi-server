package com.example.user.domain.user.presentation;

import com.example.user.domain.user.presentation.dto.req.UserCreateInput;
import com.example.user.domain.user.presentation.dto.req.UserDeleteInput;
import com.example.user.domain.user.presentation.dto.req.UserPasswordUpdateInput;
import com.example.user.domain.user.presentation.dto.req.UserUpdateInput;
import com.example.user.domain.user.presentation.dto.res.UserCommandResponse;
import com.example.user.domain.user.presentation.dto.res.UserQueryResponse;
import com.example.user.domain.user.service.CommandUserService;
import com.example.user.domain.user.service.QueryUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommandUserService commandUserService;
    private final QueryUserService queryUserService;

    @QueryMapping
    public List<UserQueryResponse> getAllUser(){
        return queryUserService.getUsers();
    }

    @QueryMapping
    public UserQueryResponse getUserById(
            @Argument Long userId
    ){
        return queryUserService.getUser(userId);
    }

    @QueryMapping
    public UserQueryResponse getUserByAccessToken(
            @Header String accessToken
    ){
        return queryUserService.getUserByAccessToken(accessToken);
    }

    @MutationMapping
    public UserCommandResponse createUser(
            @Argument UserCreateInput userCreateInput
    ){
        return commandUserService.save(userCreateInput);
    }

    @MutationMapping
    public UserCommandResponse updateUser(
            @Header String accessToken,
            @Argument UserUpdateInput userUpdateInput
    ){
        return commandUserService.update(accessToken, userUpdateInput);
    }

    @MutationMapping
    public UserCommandResponse updateUserPassword(
            @Header String accessToken,
            @Argument UserPasswordUpdateInput userPasswordUpdateInput
    ){
        return commandUserService.updatePassword(accessToken, userPasswordUpdateInput);
    }

    @MutationMapping
    public UserCommandResponse deleteUser(
            @Header String accessToken
    ){
        return commandUserService.delete(accessToken);
    }


}
