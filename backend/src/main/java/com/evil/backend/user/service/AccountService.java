package com.evil.backend.user.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<Account> getUserById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> getAllUsers() {
        return (List<Account>) accountRepository.findAll();
    }
}
