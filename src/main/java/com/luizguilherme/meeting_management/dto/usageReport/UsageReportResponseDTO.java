package com.luizguilherme.meeting_management.dto.usageReport;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsageReportResponseDTO {
    private Long id;
    private String reportName;
    private LocalDateTime generatedAt;
    private String generatedBy;
    private String reportData;
}
