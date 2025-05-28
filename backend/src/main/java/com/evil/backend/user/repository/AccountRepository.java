package com.evil.backend.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.evil.backend.user.entity.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
}
