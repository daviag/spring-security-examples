package com.daviag.security.examples.jwt.repository;

import com.daviag.security.examples.jwt.model.Role;
import com.daviag.security.examples.jwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName roleName);
}
