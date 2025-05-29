package com.evil.backend.security;

import com.evil.backend.user.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class RoleSecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public RoleSecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/**").permitAll() // remove ** afther testing
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/users")
                    .hasAuthority(AuthorityUtil.authority(PrivilegeType.READ_ACCESS))
                    .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                    .hasAuthority(AuthorityUtil.authority(PrivilegeType.KICK_USERS))
                    .requestMatchers(HttpMethod.GET, "/api/server/status")
                    .hasAuthority(AuthorityUtil.authority(PrivilegeType.READ_ACCESS))
                    .requestMatchers(HttpMethod.PUT, "/api/server/settings")
                    .hasAuthority(AuthorityUtil.authority(PrivilegeType.MANAGE_SERVER))
                    .anyRequest().denyAll()
                )
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authBuilder.userDetailsService(userDetailsService)
                   .passwordEncoder(passwordEncoder());
        return authBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
    }
}
