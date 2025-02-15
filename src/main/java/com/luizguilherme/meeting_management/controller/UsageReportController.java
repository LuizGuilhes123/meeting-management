package com.luizguilherme.meeting_management.controller;

import com.luizguilherme.meeting_management.dto.usageReport.UsageReportRequestDTO;
import com.luizguilherme.meeting_management.dto.usageReport.UsageReportResponseDTO;
import com.luizguilherme.meeting_management.mapper.UsageReportMapper;
import com.luizguilherme.meeting_management.model.UsageReport;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.service.UsageReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
public class UsageReportController {

    @Autowired
    private UsageReportService usageReportService;

    @Autowired
    private UsageReportMapper usageReportMapper;

    @PostMapping("/generate")
    public ResponseEntity<UsageReportResponseDTO> generateReport(@AuthenticationPrincipal User currentUser,
                                                                 @RequestBody UsageReportRequestDTO usageReportRequestDTO) {
        UsageReport report = usageReportService.generateReport(currentUser, usageReportRequestDTO.getReportData());
        UsageReportResponseDTO responseDTO = usageReportMapper.toUsageReportResponseDTO(report);
        return ResponseEntity.ok(responseDTO);
    }
}
