package com.evil.backend.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.evil.backend.user.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
    Optional<Account> findByUsernameOrEmail(String username, String email);
}
