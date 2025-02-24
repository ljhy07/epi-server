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
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
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
            @Argument String accessToken
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
            @Argument UserUpdateInput userUpdateInput
    ){
        return commandUserService.update(userUpdateInput);
    }

    @MutationMapping
    public UserCommandResponse updateUserPassword(
            @Argument UserPasswordUpdateInput userPasswordUpdateInput
    ){
        return commandUserService.updatePassword(userPasswordUpdateInput);
    }

    @MutationMapping
    public UserCommandResponse deleteUser(
            @Argument UserDeleteInput userDeleteInput
    ){
        return commandUserService.delete(userDeleteInput);
    }


}
