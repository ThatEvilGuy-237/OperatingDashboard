// package com.evil.backend.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// @Configuration
// public class SecurityConfig {
//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf(csrf -> csrf.disable())
//             .headers(headers -> headers.frameOptions().disable()) // allow H2 iframe
//             .authorizeHttpRequests(auth -> auth
//                 .requestMatchers("/h2-console/**").permitAll() // allow H2 access
//                 .anyRequest().permitAll()
//             );

//         return http.build();
//     }
// }
