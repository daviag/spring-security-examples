package com.daviag.security.examples.jwt.repository;

import com.daviag.security.examples.jwt.model.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<SecuredUser, Long> {
    boolean existsByEmail(String email);
    Optional<SecuredUser> findByEmail(String email);
    Optional<SecuredUser> findByPassword(String password);
}
