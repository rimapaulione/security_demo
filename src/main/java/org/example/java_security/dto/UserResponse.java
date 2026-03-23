package org.example.java_security.dto;

import org.example.java_security.model.Role;

public record UserResponse(
       Long id,
       String username,
       Role role

) {
}
