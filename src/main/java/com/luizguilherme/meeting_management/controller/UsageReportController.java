package com.luizguilherme.meeting_management.controller;


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

    @PostMapping("/generate")
    public ResponseEntity<UsageReport> generateReport(@AuthenticationPrincipal User currentUser,
                                                      @RequestBody String reportData) {
        UsageReport report = usageReportService.generateReport(currentUser, reportData);
        return ResponseEntity.ok(report);
    }
}
