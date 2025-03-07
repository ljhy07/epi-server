package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.LoginInput;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.ReIssuer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final ReIssuer reIssuer;

    @MutationMapping
    public Token login(
            @Argument LoginInput loginInput
    ){
        return
    }

    @MutationMapping
    public Token reissueToken(
            @Argument ReissueTokenInput reissueTokenInput
    ){
        return reIssuer.reissue(reissueTokenInput);
    }

    // 인증 인가용 method
    @QueryMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean checkAuthStatus(){
        return
    }
}
