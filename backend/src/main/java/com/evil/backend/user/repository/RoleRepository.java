package com.evil.backend.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.evil.backend.user.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
    Iterable<Role> findAllByOrderByPositionAsc();
}
