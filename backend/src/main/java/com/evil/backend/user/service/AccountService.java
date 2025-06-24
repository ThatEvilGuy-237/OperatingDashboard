package com.evil.backend.user.service;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

import  org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<Account> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page,size); 
        return accountRepository.findAll(pageable);
    }
}
