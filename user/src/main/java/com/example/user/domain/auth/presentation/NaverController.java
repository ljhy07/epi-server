package com.example.user.domain.auth.presentation;

import com.example.user.domain.auth.presentation.dto.req.OAuthCodeLoginInput;
import com.example.user.domain.auth.presentation.dto.res.Token;
import com.example.user.domain.auth.service.implementation.naver.NaverAuthLinkService;
import com.example.user.domain.auth.service.implementation.naver.NaverAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class NaverController {
    private final NaverAuthLinkService naverAuthLinkService;
    private final NaverAuthService naverAuthService;

    @QueryMapping
    public String getNaverAuthLink() {
        return naverAuthLinkService.execute();
    }

    @MutationMapping
    public Token oauthCodeLogin(@Argument OAuthCodeLoginInput oauthLoginInput) {
        return naverAuthService.execute(oauthLoginInput);
    }
}
