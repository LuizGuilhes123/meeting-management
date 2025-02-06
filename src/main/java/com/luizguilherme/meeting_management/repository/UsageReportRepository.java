package com.luizguilherme.meeting_management.repository;


import com.luizguilherme.meeting_management.model.UsageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsageReportRepository extends JpaRepository<UsageReport, Long> {
}
