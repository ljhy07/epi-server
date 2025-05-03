package com.example.user.application.email.presentation.dto.res;

import com.example.user.application.email.exception.value.EmailVerificationStatus;

public record EmailResponse (
        EmailVerificationStatus status,
        Boolean isSuccess
){
}
