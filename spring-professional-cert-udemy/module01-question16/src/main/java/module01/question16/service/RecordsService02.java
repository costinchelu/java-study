package module01.question16.service;

import module01.question16.backup.DbRecordsBackup;
import module01.question16.bls.DbRecordsProcessor;
import module01.question16.reader.DbRecordsReader;
import module01.question16.writer.DbRecordsWriter;
import org.springframework.stereotype.Service;

@Service
public class RecordsService02 {

    public RecordsService02(DbRecordsReader recordsReader,
                            DbRecordsBackup recordsBackup,
                            DbRecordsProcessor recordsProcessor,
                            DbRecordsWriter recordsWriter) {
        System.out.println(
                getClass().getSimpleName() + " recordsReader = " + recordsReader + "\n" +
                        getClass().getSimpleName() + " recordsBackup = " + recordsBackup + "\n" +
                        getClass().getSimpleName() + " recordsProcessor = " + recordsProcessor + "\n" +
                        getClass().getSimpleName() + " recordsWriter = " + recordsWriter + "\n"
        );
    }
}
