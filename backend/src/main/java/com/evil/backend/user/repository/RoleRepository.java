package com.evil.backend.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.evil.backend.user.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
