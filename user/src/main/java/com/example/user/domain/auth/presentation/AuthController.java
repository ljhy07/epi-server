package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.LoginInput;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.req.SignUpInput;
import com.example.user.domain.auth.presentation.dto.res.SignUpResponse;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.CommandAuthService;
import com.example.user.domain.user.service.CommandUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final CommandAuthService commandAuthService;
    private final CommandUserService commandUserService;

    @MutationMapping
    public SignUpResponse signup(
            @Argument(name = "signUpInput") SignUpInput signUpInput
    ){
        return commandUserService.save(signUpInput);
    }

    @MutationMapping
    public Token login(
            @Argument(name = "loginInput") LoginInput loginInput
    ){
        return commandAuthService.login(loginInput);
    }

    @MutationMapping
    public Token reissueToken(
            @Argument(name = "reissueTokenInput") ReissueTokenInput reissueTokenInput
    ){
        return commandAuthService.reissue(reissueTokenInput);
    }
}
