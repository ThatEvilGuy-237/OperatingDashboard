package com.evil.backend.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.evil.backend.user.entity.Privilege;
import com.evil.backend.user.entity.PrivilegeType;

import java.util.Optional;

public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Optional<Privilege> findByType(PrivilegeType type);
}
