package com.example.user.global.feign.auth.naver.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverAuthResponse {
    private String access_token;
}
