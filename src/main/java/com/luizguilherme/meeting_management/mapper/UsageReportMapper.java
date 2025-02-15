package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.usageReport.UsageReportRequestDTO;
import com.luizguilherme.meeting_management.dto.usageReport.UsageReportResponseDTO;
import com.luizguilherme.meeting_management.model.UsageReport;
import com.luizguilherme.meeting_management.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsageReportMapper {

    public UsageReport toUsageReport(UsageReportRequestDTO usageReportRequestDTO, User user) {
        UsageReport usageReport = new UsageReport();
        usageReport.setReportName("Report Generated by " + user.getUsername());
        usageReport.setGeneratedAt(LocalDateTime.now());
        usageReport.setGeneratedBy(user);
        usageReport.setReportData(usageReportRequestDTO.getReportData());
        return usageReport;
    }

    public UsageReportResponseDTO toUsageReportResponseDTO(UsageReport usageReport) {
        UsageReportResponseDTO usageReportResponseDTO = new UsageReportResponseDTO();
        usageReportResponseDTO.setId(usageReport.getId());
        usageReportResponseDTO.setReportName(usageReport.getReportName());
        usageReportResponseDTO.setGeneratedAt(usageReport.getGeneratedAt());
        usageReportResponseDTO.setGeneratedBy(usageReport.getGeneratedBy().getUsername());
        usageReportResponseDTO.setReportData(usageReport.getReportData());
        return usageReportResponseDTO;
    }
}
