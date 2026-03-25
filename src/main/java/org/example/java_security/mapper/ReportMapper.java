package org.example.java_security.mapper;


import org.example.java_security.dto.ReportResponse;
import org.example.java_security.model.Report;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ReportMapper {
    ReportResponse toResponse(Report report);
}
