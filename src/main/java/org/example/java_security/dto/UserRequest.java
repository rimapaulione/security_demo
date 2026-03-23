package org.example.java_security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequest(
        @NotBlank
        String username,

        @NotBlank
        @Size(min = 4)
        String password,

        @Email
        @NotBlank
        String email,

        String phone

) {
}
