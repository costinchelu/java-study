package module01.question24.profiles.definitions.configuration.level;

import module01.question24.profiles.definitions.configuration.level.dao.DatabaseStoreFinancialDataDao;
import module01.question24.profiles.definitions.configuration.level.dao.FinancialDataDao;
import module01.question24.profiles.definitions.configuration.level.writer.DatabaseStoreFinancialReportWriter;
import module01.question24.profiles.definitions.configuration.level.writer.FinancialReportWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("database")
public class DatabaseApplicationConfiguration {

    @Bean
    public FinancialDataDao databaseStoreFinancialDataDao() {
        return new DatabaseStoreFinancialDataDao();
    }

    @Bean
    public FinancialReportWriter databaseStoreFinancialReportWriter() {
        return new DatabaseStoreFinancialReportWriter();
    }
}
