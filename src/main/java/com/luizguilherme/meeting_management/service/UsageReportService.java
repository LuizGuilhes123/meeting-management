package com.luizguilherme.meeting_management.service;


import com.luizguilherme.meeting_management.model.UsageReport;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.UsageReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsageReportService {

    @Autowired
    private UsageReportRepository usageReportRepository;

    public UsageReport generateReport(User user, String reportData) {
        UsageReport usageReport = new UsageReport();
        usageReport.setReportName("Relatório de Uso");
        usageReport.setGeneratedAt(LocalDateTime.now());
        usageReport.setGeneratedBy(user);
        usageReport.setReportData(reportData);

        return usageReportRepository.save(usageReport);
    }
}
