package com.jwtworking.repository;

import com.jwtworking.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.SecureRandom;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
 Optional<Role> findByName(String name);
}
