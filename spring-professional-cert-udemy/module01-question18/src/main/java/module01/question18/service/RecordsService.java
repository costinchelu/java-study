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
    @Autowired
    @Qualifier("dbRecordsProcessor")
    private RecordsProcessor recordsProcessor;

    @Autowired
    @Qualifier("file-records-reader")
    private RecordsReader recordsReader;

    @Autowired
    @Qualifier("db-writer")
    private RecordsWriter recordsWriter;

    @Autowired
    @RecordsValidatorType(RecordsValidatorType.RecordsValidatorMode.FILE)
    private RecordsValidator recordsValidator;
}
