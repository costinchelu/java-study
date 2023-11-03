package module01.question03.service;

import module01.question03.dao.MockFinancialDataDao;
import module01.question03.writer.MockFinancialReportWriter;
import org.junit.Test;

public class SimpleFinancialReportServiceTest {

    @Test
    public void shouldGenerateReportOnManualMockTypes() {
        SimpleFinancialReportService simpleFinancialReportService = new SimpleFinancialReportService(
                new MockFinancialDataDao(), new MockFinancialReportWriter()
        );

        simpleFinancialReportService.generateReport();
    }
}