package com.example.user.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    @MutationMapping
    public void login(){}

    @MutationMapping
    public void oauthLogin(){}

    @MutationMapping
    public void reissueToken(){

    }

    // 인증 인가용 method
    @QueryMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean checkAuthStatus(){
        // 인증 인가 확인 로직
    }
}
