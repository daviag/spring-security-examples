package com.daviag.security.examples.jwt.dto;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken
) {
}
