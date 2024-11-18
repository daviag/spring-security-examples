package com.daviag.security.examples.jwt.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshTokenRequestDTO(
        @NotBlank
        String token
) {
}
