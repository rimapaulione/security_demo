package org.example.java_security.dto;

import java.util.Map;

public record UsersSummaryResponse(
        long totalUsers,
        Map<String, Long> usersByRole,
        long recentActions
) {
}
