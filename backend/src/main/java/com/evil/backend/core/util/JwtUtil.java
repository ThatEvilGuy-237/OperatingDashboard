package com.evil.backend.core.util;

import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.evil.backend.user.entity.Account;
import com.evil.backend.user.entity.Role;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationMs;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    @Value("${jwt.audience}")
    private String jwtAudience;

    public String generateToken(Account account) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // Collect role names
        Set<String> roles = account.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        // Collect privilege names
        Set<String> privileges = account.getRoles().stream()
                .flatMap(role -> role.getPrivileges().stream())
                .map(priv -> priv.getType().name())
                .collect(Collectors.toSet());
        // Create secret key properly
        Key key = Keys.hmacShaKeyFor(jwtSecret.getBytes());

        return Jwts.builder()
                .setSubject(account.getUsername())
                .setIssuer(jwtIssuer)
                .setAudience(jwtAudience)
                .claim("roles", roles)
                .claim("privileges", privileges)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();

    }
}