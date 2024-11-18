package com.daviag.security.examples.jwt.dto;

public record RefreshTokenResponseDTO(
        String accessToken,
        String refreshToken
) {
}
