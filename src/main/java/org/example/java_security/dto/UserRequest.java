package org.example.java_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank
        String username,
        @NotBlank
        String password,

        @Email
        String email,

        String phone

) {
}
