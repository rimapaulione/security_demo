package org.example.java_security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserRequest;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.service.AuthService;
import org.example.java_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserRequest newUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.create(newUser));
    }

    @GetMapping("/login")
    public String userEndpoint() {
        return "This is user and admin endpoint";
    }

}
