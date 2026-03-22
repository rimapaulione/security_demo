package org.example.java_security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.UserRequest;
import org.example.java_security.dto.UserResponse;
import org.example.java_security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ApiController {

    private final UserService userService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is public endpoint";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "This is user and admin endpoint";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "This is admin only endpoint";
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest newUser) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(newUser));

    }

}
