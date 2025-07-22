package com.evil.backend.user.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.evil.backend.user.dto.AccountDto;
import com.evil.backend.user.entity.Account;
import com.evil.backend.user.entity.Role;
import com.evil.backend.user.repository.AccountRepository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final EntityManager entityManager;
    //# GET
    public AccountDto getUserById(Long id) {
        return accountRepository.findById(id)
            .map(AccountDto::from)
            .orElse(null);
    }


    public Page<AccountDto> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return accountRepository.findAllBy(pageable)
        .map(AccountDto::from);
    }

    //# DELETE, REMOVE
    public AccountDto deleteUser(Long id) {
        Account account = accountRepository.findById(id).get();
        accountRepository.delete(account);

        return AccountDto.from(account);
    }

    public AccountDto removeRole(Long accountId, Long roleId) {
        Account account = accountRepository.findById(accountId)
            .orElse(null); // TODo: null handling

        if (account == null) {
            
        }

        Role roleRef = entityManager.getReference(Role.class, roleId);
        account.getRoles().remove(roleRef);
        Account saved = accountRepository.save(account);
        return AccountDto.from(saved);
    }
    //# UPDATE,ASSIGN
    public AccountDto updateUser(Long id, AccountDto accountDto) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            return null; // Todo: null handeling
        }

        Account account = optionalAccount.get();

        account.setUsername(accountDto.username());
        account.setEmail(accountDto.email());
        account.setFirstName(accountDto.firstName());
        account.setLastName(accountDto.lastName());
        account.setPhoneNumber(accountDto.phoneNumber());
        account.setRoles(accountDto.roles());
        account.setValidated(accountDto.validated());
        account.setLocked(accountDto.locked());
        account.setJobTitle(accountDto.jobTitle());

        Account saved = accountRepository.save(account);

        return AccountDto.from(saved);
    }

    public AccountDto assignRole(Long accountId, Long roleId) {
        Account account = accountRepository.findById(accountId)
            .orElse(null); // TODo: null handling

        if (account == null) {
            return null;
        }

        // virtual reference
        Role roleRef = entityManager.getReference(Role.class, roleId);

        account.getRoles().add(roleRef);
        Account saved = accountRepository.save(account);
        return AccountDto.from(saved);
    }

    public AccountDto setRoles(Long id, List<Role> roles) {
        Optional<Account> optionalAccount = accountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            return null; // TODo: null handeling
        }

        Account account = optionalAccount.get();
        account.setRoles(new HashSet<>(roles));

        Account saved = accountRepository.save(account);
        return AccountDto.from(saved);
    }  
    
    //# CREATE
    public AccountDto createUser(AccountDto accountDto) {
        Account account = accountDto.toEntity();
        // TODo: Validation
        Account saved = accountRepository.save(account);
        return AccountDto.from(saved);
    }
}
