package com.evil.backend.user.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public record AccountDto(
    Long id,
    String username,
    String email,
    String firstName,
    String lastName,
    String phoneNumber,
    Set<Role> roles,
    boolean validated,
    boolean locked,
    LocalDateTime accountCreated,
    LocalDateTime lastLogin
) {
    public static AccountDto from(Account account) {
        return AccountDto.builder()
            .id(account.getId())
            .username(account.getUsername())
            .email(account.getEmail())
            .firstName(account.getFirstName())
            .lastName(account.getLastName())
            .phoneNumber(account.getPhoneNumber())
            .roles(account.getRoles())
            .validated(account.isValidated())
            .locked(account.isLocked())
            .accountCreated(account.getAccountCreated())
            .lastLogin(account.getLastLogin())
            .build();
    }

    public Account toEntity() {
        Account account = new Account();
        account.setId(id);
        account.setUsername(username);
        account.setEmail(email);
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNumber);
        account.setRoles(roles);
        account.setValidated(validated);
        account.setLocked(locked);
        account.setAccountCreated(accountCreated);
        account.setLastLogin(lastLogin);
        return account;
    }
}
