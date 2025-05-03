package com.example.user.application.user.service;

import com.example.user.application.auth.presentation.dto.req.AdditionalInfoInput;
import com.example.user.application.auth.presentation.dto.req.SignUpInput;
import com.example.user.application.auth.presentation.dto.res.AdditionalInfoResponse;
import com.example.user.application.auth.presentation.dto.res.SignUpResponse;
import com.example.user.application.auth.service.implementation.AuthValidator;
import com.example.user.application.user.domain.User;
import com.example.user.application.user.domain.value.LoginType;
import com.example.user.application.user.presentation.dto.req.UserPasswordUpdateInput;
import com.example.user.application.user.presentation.dto.req.UserUpdateInput;
import com.example.user.application.user.presentation.dto.res.UserResponse;
import com.example.user.application.user.service.implementation.UserCreator;
import com.example.user.application.user.service.implementation.UserDeleter;
import com.example.user.application.user.service.implementation.UserReader;
import com.example.user.application.user.service.implementation.UserUpdater;
import com.example.user.global.auth.util.AuthUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MutationUserService {

    private final UserCreator userCreator;
    private final UserUpdater userUpdater;
    private final UserDeleter userDeleter;
    private final UserReader userReader;
    private final AuthValidator authValidator;
    private final AuthUtils authUtils;

    public SignUpResponse save(
            SignUpInput signUpInput
    ){
        userCreator.create(
                User.userCreateBuilder()
                        .name(signUpInput.name())
                        .email(signUpInput.email())
                        .password(authValidator.hashPassword(signUpInput.password()))
                        .phone(signUpInput.phone())
                        .LoginType(LoginType.NORMAL)
                        .role(signUpInput.role())
                        .build()
        );

        return new SignUpResponse(
                true
        );
    }

    public UserResponse saveOAuth(
            User user
    ){
        userCreator.create(user);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }

    public AdditionalInfoResponse additionalInfo(
            AdditionalInfoInput additionalInfoInput
    ){
        User user = userReader.findByEmail(authUtils.getCurrentUser().getEmail());

        userCreator.additionalInfo(user, additionalInfoInput);

        return new AdditionalInfoResponse(
                true
        );
    }

    public UserResponse update(
            UserUpdateInput userUpdateInput
    ){
        User updatableUser = userReader.findByEmailFromSecurity();
        User user = userUpdater.update(updatableUser, userUpdateInput);

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }

    public UserResponse updatePassword(
            UserPasswordUpdateInput userPasswordUpdateInput
    ){
        User updatableUser = userReader.findByEmailFromSecurity();
        User user = userUpdater.updatePassword(updatableUser, userPasswordUpdateInput.password());

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getLoginType(),
                user.getRole()
        );
    }

    public UserResponse delete(){
        User updatableUser = userReader.findByEmailFromSecurity();
        User user = userDeleter.delete(updatableUser);

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
