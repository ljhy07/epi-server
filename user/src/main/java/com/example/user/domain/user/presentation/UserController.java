package com.example.user.domain.user.presentation;

import com.example.user.domain.user.presentation.dto.req.UserPasswordUpdateInput;
import com.example.user.domain.user.presentation.dto.req.UserUpdateInput;
import com.example.user.domain.user.presentation.dto.res.UserResponse;
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
    public List<UserResponse> getAllUser(){
        return queryUserService.getUsers();
    }

    @QueryMapping
    public UserResponse getUserById(
            @Argument Long userId
    ){
        return queryUserService.getUser(userId);
    }

    @QueryMapping
    public UserResponse getUserByAccessToken(){
        return queryUserService.getUserByAccessToken();
    }

//    @MutationMapping
//    public UserResponse createUser(
//            @Argument UserCreateInput userCreateInput
//    ){
//        return commandUserService.save(userCreateInput);
//    }

    @MutationMapping
    public UserResponse updateUser(
            @Argument UserUpdateInput userUpdateInput
    ){
        return commandUserService.update(userUpdateInput);
    }

    @MutationMapping
    public UserResponse updateUserPassword(
            @Argument UserPasswordUpdateInput userPasswordUpdateInput
    ){
        return commandUserService.updatePassword(userPasswordUpdateInput);
    }

    @MutationMapping
    public UserResponse deleteUser(){
        return commandUserService.delete();
    }


}
