package org.example.java_security.controller;


import lombok.RequiredArgsConstructor;
import org.example.java_security.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/users-summary")
    public ResponseEntity<String> getUserSummary() {
        return ResponseEntity.ok("REPORT SUMMARY");
    }
}
