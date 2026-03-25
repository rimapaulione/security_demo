package org.example.java_security.service;


import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.ReportResponse;
import org.example.java_security.dto.UsersSummaryResponse;
import org.example.java_security.mapper.ReportMapper;
import org.example.java_security.model.Role;
import org.example.java_security.repository.ReportRepository;
import org.example.java_security.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ReportMapper reportMapper;

    public List<ReportResponse> getAllReports() {
        return reportRepository.findAll().stream().map(reportMapper::toResponse).toList();
    }

    public UsersSummaryResponse getUsersSummary() {
        long totalUsers = userRepository.count();

        Map<String, Long> usersByRole = Arrays.stream(Role.values())
                .collect(Collectors.toMap(
                        Role::name,
                        userRepository::countByRole
                ));

        long recentActions = reportRepository.countByDateAfter(LocalDateTime.now().minusDays(7));

        return new UsersSummaryResponse(totalUsers, usersByRole, recentActions);
    }
}
