package com.example.user.domain.email.presentation.dto.res;

import com.example.user.domain.email.exception.value.EmailVerificationStatus;

public record EmailResponse (
        EmailVerificationStatus status,
        Boolean isSuccess
){
}
