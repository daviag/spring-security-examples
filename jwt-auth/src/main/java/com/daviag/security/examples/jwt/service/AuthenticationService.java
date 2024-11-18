package com.daviag.security.examples.jwt.service;

import com.daviag.security.examples.jwt.dto.LoginRequestDTO;
import com.daviag.security.examples.jwt.dto.LoginResponseDTO;
import com.daviag.security.examples.jwt.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtils jwtUtils;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String accessToken = jwtUtils.createJwt(loginRequestDTO.username(),
                    new ArrayList<>(authentication.getAuthorities()));
            String refreshToken = refreshTokenService.generateToken((UserDetails) authentication.getPrincipal())
                    .getToken();
            return new LoginResponseDTO(accessToken, refreshToken);
        } catch (AuthenticationException e) {
            // Handle authentication exception and return a user-friendly error message
            throw new RuntimeException("Invalid username or password", e);
        }
    }
}
