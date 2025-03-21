package com.example.user.domain.address.presentation.dto.req;

import jakarta.annotation.Nullable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public record DeleteAddressInput(
        Long addressId
) {
}
