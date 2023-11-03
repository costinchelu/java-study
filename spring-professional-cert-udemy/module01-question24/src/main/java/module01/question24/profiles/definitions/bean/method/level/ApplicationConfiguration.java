package module01.question24.profiles.definitions.bean.method.level;

import module01.question24.profiles.definitions.bean.method.level.dao.DatabaseStoreFinancialDataDao;
import module01.question24.profiles.definitions.bean.method.level.dao.FileStoreFinancialDataDao;
import module01.question24.profiles.definitions.bean.method.level.dao.FinancialDataDao;
import module01.question24.profiles.definitions.bean.method.level.service.FinancialReportService;
import module01.question24.profiles.definitions.bean.method.level.writer.DatabaseStoreFinancialReportWriter;
import module01.question24.profiles.definitions.bean.method.level.writer.FileStoreFinancialReportWriter;
import module01.question24.profiles.definitions.bean.method.level.writer.FinancialReportWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public FinancialReportService financialReportService(FinancialDataDao financialDataDao, FinancialReportWriter financialReportWriter) {
        return new FinancialReportService(financialDataDao, financialReportWriter);
    }

    @Bean
    @Profile("database")
    public FinancialDataDao databaseStoreFinancialDataDao() {
        return new DatabaseStoreFinancialDataDao();
    }

    @Bean
    @Profile("database")
    public FinancialReportWriter databaseStoreFinancialReportWriter() {
        return new DatabaseStoreFinancialReportWriter();
    }

    @Bean
    @Profile("file")
    public FinancialDataDao fileStoreFinancialDataDao() {
        return new FileStoreFinancialDataDao();
    }

    @Bean
    @Profile("file")
    public FinancialReportWriter fileStoreFinancialReportWriter() {
        return new FileStoreFinancialReportWriter();
    }
}
