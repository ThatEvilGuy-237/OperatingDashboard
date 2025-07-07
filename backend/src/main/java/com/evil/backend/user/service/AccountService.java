package com.evil.backend.user.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evil.backend.user.dto.AccountDto;
import com.evil.backend.user.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Optional<AccountDto> getUserById(Long id) {
        return accountRepository.findById(id)
            .map(AccountDto::from);
    } 

    public Page<AccountDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAllBy(pageable)
        .map(AccountDto::from);
    }
}
