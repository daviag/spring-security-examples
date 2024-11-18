package com.daviag.security.examples.jwt.repository;

import com.daviag.security.examples.jwt.model.RefreshToken;
import com.daviag.security.examples.jwt.model.SecuredUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findBySecuredUser(SecuredUser securedUser);
}
