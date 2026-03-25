package org.example.java_security.repository;

import org.example.java_security.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ReportRepository extends JpaRepository<Report, Long> {

    long countByDateAfter(LocalDateTime date);
}
