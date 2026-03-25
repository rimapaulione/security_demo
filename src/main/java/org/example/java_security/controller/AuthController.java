package org.example.java_security.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserRequest;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.service.AuthService;
import org.example.java_security.service.RateLimitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final RateLimitService rateLimitService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest newUser,
                                                     HttpServletRequest request) {
        rateLimitService.checkRegistrationLimit(request.getRemoteAddr());
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.create(newUser));
    }

}
