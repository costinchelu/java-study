package module01.question17.service;

import module01.question17.ds.Report;
import module01.question17.writer.ReportWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    @Autowired
    private ReportWriter reportWriter;

    @Value("${report.global.name}")
    private String reportGlobalName;

    public void execute() {
        Report report = new Report();

        // ...
        System.out.println(reportGlobalName);
        reportWriter.write(report, reportGlobalName);
    }
}
