package com.example.user.application.auth.service.implementation.google;

import com.example.user.application.auth.presentation.dto.req.OAuthTokenLoginInput;
import com.example.user.application.auth.presentation.dto.res.Token;
import com.example.user.application.user.domain.User;
import com.example.user.application.user.domain.value.LoginType;
import com.example.user.application.user.domain.value.Role;
import com.example.user.application.user.presentation.dto.res.UserResponse;
import com.example.user.application.user.service.MutationUserService;
import com.example.user.application.user.service.QueryUserService;
import com.example.user.global.feign.auth.google.GoogleInformationClient;
import com.example.user.global.feign.auth.google.dto.res.GoogleInformationResponse;
import com.example.user.global.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GoogleAuthService {

    private final GoogleInformationClient googleInformationClient;
    private final MutationUserService mutationUserService;
    private final QueryUserService queryUserService;
    private final JwtUtils jwtUtils;

    @Transactional
    public Token execute(OAuthTokenLoginInput oauthTokenLoginInput) {
        GoogleInformationResponse response = googleInformationClient
                .getUserInformation(oauthTokenLoginInput.accessToken());
        String email = response.getEmail();

        UserResponse user = queryUserService.getUserByEmail(email);

        if (user == null) {
            mutationUserService.saveOAuth(
                    User.oauthUserCreateBuilder()
                        .email(email)
                        .loginType(LoginType.GOOGLE)
                        .role(Role.User)
                        .build()
            );
        }

        return jwtUtils.getToken(email);
    }
}
