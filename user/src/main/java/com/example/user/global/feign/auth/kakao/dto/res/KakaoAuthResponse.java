package com.example.user.global.feign.auth.kakao.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoAuthResponse {
    private String access_token;
}
