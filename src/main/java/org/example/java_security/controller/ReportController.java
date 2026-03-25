package org.example.java_security.controller;


import lombok.RequiredArgsConstructor;
import org.example.java_security.dto.ReportResponse;
import org.example.java_security.dto.UsersSummaryResponse;
import org.example.java_security.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/users-summary")
    public ResponseEntity<UsersSummaryResponse> getUsersSummary() {
        return ResponseEntity.ok(reportService.getUsersSummary());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("")
    public ResponseEntity<List<ReportResponse>> getAllReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }
}
