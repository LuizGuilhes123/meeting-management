package service;

import com.luizguilherme.meeting_management.model.UsageReport;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.UsageReportRepository;
import com.luizguilherme.meeting_management.service.UsageReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsageReportServiceTest {

    @Mock
    private UsageReportRepository usageReportRepository;

    @InjectMocks
    private UsageReportService usageReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateReport() {
        User user = new User();
        String reportData = "Sample report data";
        UsageReport usageReport = new UsageReport();
        usageReport.setReportName("Relatório de Uso");
        usageReport.setGeneratedAt(LocalDateTime.now());
        usageReport.setGeneratedBy(user);
        usageReport.setReportData(reportData);

        when(usageReportRepository.save(any(UsageReport.class))).thenReturn(usageReport);

        UsageReport result = usageReportService.generateReport(user, reportData);

        assertNotNull(result);
        assertEquals("Relatório de Uso", result.getReportName());
        assertEquals(reportData, result.getReportData());
        verify(usageReportRepository, times(1)).save(any(UsageReport.class));
    }
}
