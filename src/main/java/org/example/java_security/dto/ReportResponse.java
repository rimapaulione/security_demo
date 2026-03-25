package org.example.java_security.dto;

import org.example.java_security.model.UserActionType;

import java.time.LocalDateTime;

public record ReportResponse(
        Long id,
        UserActionType status,
        Long userId,
        LocalDateTime date,
        String description,
        String performedBy
) {
}
