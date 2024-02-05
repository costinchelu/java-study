package module01.question16.reader;

import module01.question16.ds.Record;
import org.springframework.stereotype.Component;

import javax.annotation.Priority;
import java.util.Collection;

@Component
@Priority(2)
public class WebServiceRecordsReader implements RecordsReader {

    @Override
    public Collection<Record> readRecords() {
        return null;
    }
}
