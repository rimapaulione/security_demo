package org.example.java_security.event;

import org.example.java_security.model.Role;

import java.time.LocalDateTime;

public record UserRoleChangeEvent(
        Long userId,
        String username,
        Role role,
        Role newRole,
        String performedBy,
        LocalDateTime date
) {
}
