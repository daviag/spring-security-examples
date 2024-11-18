package com.daviag.security.examples.jwt.config;

import com.daviag.security.examples.jwt.model.Role;
import com.daviag.security.examples.jwt.model.RoleName;
import com.daviag.security.examples.jwt.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class InitConfig {

    @Bean
    CommandLineRunner runner(RoleRepository roleRepository) {
        return args -> {
            Arrays.stream(RoleName.values()).forEach(r -> roleRepository.save(new Role(null, r)));
        };
    }
}
