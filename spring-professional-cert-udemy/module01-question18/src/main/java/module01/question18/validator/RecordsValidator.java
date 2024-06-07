package module01.question18.validator;

import module01.question18.ds.Record;

import java.util.Collection;

public interface RecordsValidator {

    void validate(Collection<Record> records);
}
