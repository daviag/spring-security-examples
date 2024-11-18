package com.daviag.security.examples.jwt.controller;

import com.daviag.security.examples.jwt.dto.*;
import com.daviag.security.examples.jwt.service.AuthenticationService;
import com.daviag.security.examples.jwt.service.RefreshTokenService;
import com.daviag.security.examples.jwt.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        registrationService.register(registerRequestDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return authenticationService.login(loginRequestDTO);
    }

    @PostMapping("/refresh")
    public RefreshTokenResponseDTO refreshToken(@RequestBody @Valid RefreshTokenRequestDTO refreshTokenRequestDTO) {
        return refreshTokenService.refresh(refreshTokenRequestDTO.token());
    }
}
