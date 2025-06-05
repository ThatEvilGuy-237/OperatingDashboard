package com.evil.backend.xsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;

import com.evil.backend.user.entity.PrivilegeType;

@Configuration
@EnableWebSecurity
public class RoleSecurityConfig {

    private final SecurityUserDetailsService userDetailsService;
    
    public RoleSecurityConfig(SecurityUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults()) // <-- Add this line
                .headers(headers -> headers.frameOptions().disable()) // Allow H2 console in frames
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/h2-console/**").permitAll() // Always allow H2 console
                    .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll() // Allow login without authentication
                    .requestMatchers(HttpMethod.GET, "/api/users")
                        .hasAuthority(PrivilegeType.READ_ACCESS.name())
                    .requestMatchers(HttpMethod.DELETE, "/api/users/**")
                        .hasAuthority(PrivilegeType.KICK_USERS.name())
                    .requestMatchers(HttpMethod.GET, "/api/server/status")
                        .hasAuthority(PrivilegeType.READ_ACCESS.name())
                    .requestMatchers(HttpMethod.PUT, "/api/server/settings")
                        .hasAuthority(PrivilegeType.MANAGE_SERVER.name())
                    .requestMatchers("/api/**").authenticated() // All other /api/** require authentication
                    .anyRequest().denyAll() // Deny everything else
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
