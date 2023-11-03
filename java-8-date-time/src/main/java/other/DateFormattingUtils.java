package other;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DateFormattingUtils {

    private final static String DATE_FORMAT = "yyyy-mm-dd";
    private final static String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss";
    Logger logger = Logger.getLogger(DateFormattingUtils.class.getName());

    public String today() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public List<String> between(String start, String end, String format) {
        List<String> dateRange = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start, DateTimeFormatter.ofPattern(DATE_FORMAT));
        LocalDate endDate = LocalDate.parse(end, DateTimeFormatter.ofPattern(DATE_FORMAT));
        long dates = ChronoUnit.DAYS.between(startDate, endDate);
        for (long i = dates; i >= 0; i--) {
            dateRange.add(startDate.plusDays(i)
                    .format(DateTimeFormatter.ofPattern(StringUtils.isBlank(format) ? DATE_FORMAT : format)));
        }
        return dateRange.stream().sorted().collect(Collectors.toList());
    }

    public String nextDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDate.parse(date, formatter).plusDays(1).format(formatter);
    }

    public List<String> untilToday(String start, String format) {
        return between(start, today(), format);
    }

    public String formatTimestamp(String source, String sourceFormat, String targetFormat) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(sourceFormat))
                .format(DateTimeFormatter.ofPattern(targetFormat));
    }

    public String toTimestamp(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            SimpleDateFormat systemDateFormat = new SimpleDateFormat(TIMESTAMP_FORMAT);
            return systemDateFormat.format(dateFormat.parse(date));
        } catch (ParseException e) {
            logger.log(Level.WARNING, MessageFormat.format("Error parsing date : {0} , SourceFormat : {1} , Target Format {2}", date,
                    DATE_FORMAT, TIMESTAMP_FORMAT), e);
            return date;
        }
    }
}

