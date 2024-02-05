package module01.question16.reader;

import module01.question16.ds.Record;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@Order(1)
public class SocketRecordsReader implements RecordsReader {

    @Override
    public Collection<Record> readRecords() {
        return null;
    }
}
