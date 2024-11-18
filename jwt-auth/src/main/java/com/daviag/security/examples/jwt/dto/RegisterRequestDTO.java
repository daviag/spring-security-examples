package com.daviag.security.examples.jwt.dto;

import com.daviag.security.examples.jwt.model.RoleName;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RegisterRequestDTO(
        @NotBlank
        String firstName,
        @NotBlank
        String lastName,
        @NotBlank
        String email,
        @NotBlank
        String password,

        List<RoleName> roles
) {
}
