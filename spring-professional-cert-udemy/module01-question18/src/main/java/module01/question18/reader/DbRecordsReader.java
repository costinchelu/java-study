package module01.question18.reader;

import module01.question18.ds.Record;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;

@Component("db-records-reader")
public class DbRecordsReader implements RecordsReader {

    @Override
    public Collection<Record> readRecords() {
        return Collections.emptyList();
    }
}
