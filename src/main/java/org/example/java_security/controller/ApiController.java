package org.example.java_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

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
}
