package module01.question17.writer;

import module01.question17.ds.Report;
import org.springframework.stereotype.Component;

@Component
public class DbReportWriter implements ReportWriter {

    @Override
    public void write(Report report, String reportName) {

    }
}
