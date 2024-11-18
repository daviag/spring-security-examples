package com.daviag.security.examples.jwt.service;

import com.daviag.security.examples.jwt.dto.RefreshTokenResponseDTO;
import com.daviag.security.examples.jwt.model.RefreshToken;
import com.daviag.security.examples.jwt.model.SecuredUser;
import com.daviag.security.examples.jwt.repository.RefreshTokenRepository;
import com.daviag.security.examples.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private static final Long DEFAULT_EXPIRATION = 10L;
    @Value("${jwt.refresh.expirationTime:DEFAULT_EXPIRATION}")
    private Long expiration;

    private final JwtUtils jwtUtils;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateToken(UserDetails userDetails) {
        RefreshToken refreshToken = refreshTokenRepository
                .findBySecuredUser((SecuredUser) userDetails)
                .orElseGet(RefreshToken::new);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiration(Instant.now().plus(expiration, ChronoUnit.MINUTES));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshTokenResponseDTO refresh(String token) {
        try {
            // Validate the refresh token
            RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                    .orElseThrow(() -> new CredentialsExpiredException("Invalid refresh token"));

            // Check if the refresh token has expired
            if (refreshToken.getExpiration().isBefore(Instant.now())) {
                throw new CredentialsExpiredException("Refresh token has expired");
            }

            // Create a new access token with the same permissions as the original token
            String accessToken = jwtUtils.createJwt(refreshToken.getSecuredUser().getUsername(),
                    new ArrayList<>(refreshToken.getSecuredUser().getAuthorities()));

            // Return the new access token and refresh token
            return new RefreshTokenResponseDTO(accessToken, token);
        } catch (CredentialsExpiredException e) {
            // Handle expired or invalid refresh tokens
            throw new RuntimeException("Invalid refresh token", e);
        }
    }
}
