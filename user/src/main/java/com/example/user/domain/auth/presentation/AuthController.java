package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.AdditionalInfoInput;
import com.example.user.domain.auth.presentation.dto.req.LoginInput;
import com.example.user.domain.auth.presentation.dto.req.ReissueTokenInput;
import com.example.user.domain.auth.presentation.dto.req.SignUpInput;
import com.example.user.domain.auth.presentation.dto.res.AdditionalInfoResponse;
import com.example.user.domain.auth.presentation.dto.res.SignUpResponse;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.MutationAuthService;
import com.example.user.domain.user.service.MutationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final MutationAuthService mutationAuthService;
    private final MutationUserService mutationUserService;

    @MutationMapping
    public SignUpResponse signup(
            @Argument(name = "signUpInput") SignUpInput signUpInput
    ){
        return mutationUserService.save(signUpInput);
    }

    @MutationMapping
    public Token login(
            @Argument(name = "loginInput") LoginInput loginInput
    ){
        return mutationAuthService.login(loginInput);
    }

    @MutationMapping
    public AdditionalInfoResponse additionalInfo(
            @Argument(name = "additionalInfoInput") AdditionalInfoInput additionalInfoInput
    ){
        return mutationUserService.additionalInfo(additionalInfoInput);
    }

    @MutationMapping
    public Token reissueToken(
            @Argument(name = "reissueTokenInput") ReissueTokenInput reissueTokenInput
    ){
        return mutationAuthService.reissue(reissueTokenInput);
    }
}
