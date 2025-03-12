package com.example.user.domain.auth.exception;

import com.example.user.global.exception.security.UserSecurityException;
import org.springframework.http.HttpStatus;

public class LimitedReissueOpportunitiesException extends UserSecurityException {
    public LimitedReissueOpportunitiesException() {
        super(HttpStatus.UNAUTHORIZED, "LIMITED_REISSUE_OPPORTUNITIES", "재발급 횟수가 없습니다.");
    }
}
