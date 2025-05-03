package com.example.user.application.auth.presentation;

import com.example.user.application.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.application.auth.presentation.dto.res.Token;
import com.example.user.application.auth.service.implementation.naver.NaverAuthLinkService;
import com.example.user.application.auth.service.implementation.naver.NaverAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
public class NaverController {
    private final NaverAuthLinkService naverAuthLinkService;
    private final NaverAuthService naverAuthService;

    @QueryMapping
    public String getNaverAuthLink() {
        return naverAuthLinkService.execute();
    }

    @MutationMapping
    public Token oauthNaverLogin(@Argument(name = "oauthLoginInput") OAuthCodeLoginInput oauthLoginInput) {
        return naverAuthService.execute(oauthLoginInput);
    }
}
