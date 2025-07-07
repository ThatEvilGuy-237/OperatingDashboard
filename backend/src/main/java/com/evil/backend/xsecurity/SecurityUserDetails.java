package com.evil.backend.xsecurity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.evil.backend.user.entity.Account;

public class SecurityUserDetails implements UserDetails {
    private final Account account;

    public SecurityUserDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Logger logger = LoggerFactory.getLogger(SecurityUserDetails.class);

        var authorities = account.getRoles().stream()
            .flatMap(role -> role.getPrivileges().stream())
            .map(priv -> new SimpleGrantedAuthority(priv.getType().name()))
            .collect(Collectors.toSet());

        logger.info("User '{}' has authorities: {}", account.getUsername(), 
            authorities.stream().map(GrantedAuthority::getAuthority).toList());

        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
