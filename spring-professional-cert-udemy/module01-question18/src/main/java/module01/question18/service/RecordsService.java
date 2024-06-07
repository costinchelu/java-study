package module01.question18.service;

import module01.question18.bls.RecordsProcessor;
import module01.question18.reader.RecordsReader;
import module01.question18.validator.RecordsValidator;
import module01.question18.validator.RecordsValidatorType;
import module01.question18.writer.RecordsWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class RecordsService {

    // using class's name
    @Autowired
    @Qualifier("dbRecordsProcessor")
    private RecordsProcessor recordsProcessor;

    // using component's name
    @Autowired
    @Qualifier("file-records-reader")
    private RecordsReader recordsReader;

    // using qualifier's name
    @Autowired
    @Qualifier("db-writer")
    private RecordsWriter recordsWriter;

    // using a custom annotation extending the @Qualifier annotation
    @Autowired
    @RecordsValidatorType(RecordsValidatorType.RecordsValidatorMode.FILE)
    private RecordsValidator recordsValidator;
}
