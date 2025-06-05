package com.evil.backend.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        try {
            JwtDTO jwt = authService.authenticateAndGenerateToken(loginDTO);
            return ResponseEntity.ok(jwt);
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody JwtDTO jwtDTO) {
        try {
            boolean isValid = authService.validateToken(jwtDTO.getToken());
            return ResponseEntity.ok(isValid);
        } catch (Exception ex) {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}