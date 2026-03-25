package org.example.java_security.event;

import org.example.java_security.model.Role;

import java.time.LocalDateTime;

public record UserDeleteEvent(
        Long userId,
        String username,
        Role role,
        String performedBy,
        LocalDateTime date
) {
}
