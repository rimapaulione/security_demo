package org.example.java_security.dto;

import jakarta.validation.constraints.NotNull;
import org.example.java_security.model.Role;

public record RoleUpdateRequest(
        @NotNull
        Role role
) {
}
