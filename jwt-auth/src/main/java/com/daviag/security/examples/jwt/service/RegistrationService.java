package com.daviag.security.examples.jwt.service;

import com.daviag.security.examples.jwt.dto.RegisterRequestDTO;
import com.daviag.security.examples.jwt.model.RoleName;
import com.daviag.security.examples.jwt.model.SecuredUser;
import com.daviag.security.examples.jwt.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public void register(RegisterRequestDTO registerRequestDTO) {
        boolean exists = userDetailsManager.userExists(registerRequestDTO.email());
        if (exists) {
            throw new RuntimeException("User already exists");
        }

        // Create new user
        List<RoleName> userRoles = new ArrayList<>(registerRequestDTO.roles());
        if (userRoles.isEmpty()) {
            userRoles.add(RoleName.USER);
        }
        SecuredUser user = new SecuredUser(null,
                registerRequestDTO.firstName(),
                registerRequestDTO.lastName(),
                registerRequestDTO.email(),
                passwordEncoder.encode(registerRequestDTO.password()),
                userRoles.stream().map(roleRepository::findByName).toList());
        userDetailsManager.createUser(user);
    }
}
