package chapter5;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;


public class StringInterconversions {

    private static final String DATE_TIME_ZONE_ID = "2011-12-03T10:15:30+01:00[Europe/Paris]";
    private static final String DOC_DATE = "2011-12-03";
    private static final String DOC_TIME = "10:53:12";

    public static void main(String[] args) {

        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(
                LocalDate.of(2011, 12, 3),
                LocalTime.of(10, 15, 30),
                ZoneId.of("Europe/Paris"));


        // string ----->>>>> temporal   (parse)
        ZonedDateTime zonedDateTime2 = ZonedDateTime.parse(DATE_TIME_ZONE_ID);
        System.out.println(zonedDateTime1.equals(zonedDateTime2));

        // parse will produce a temporal accessor
        TemporalAccessor temporalAccessor1 = DateTimeFormatter.ISO_LOCAL_DATE.parse("2011-12-03");

        // but using from static method will return the correspondent temporal
        LocalDate localDate1 = DateTimeFormatter.ISO_LOCAL_DATE.parse(DOC_DATE, LocalDate::from);

        // temporal ----->>>>> string   (format)

        // toString
        // -> 2011-12-03T10:15:30+01:00[Europe/Paris]
        System.out.println(zonedDateTime1.toString());

        // In case we want a standard (predefined) format:
        String dateStr1 = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(2011, 12, 3));
        String dateStr2 = DateTimeFormatter.ISO_LOCAL_DATE.format(zonedDateTime2);


        // or from a predefined format style
        String dateTimeStr1 =
                DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM)
                        .withLocale(Locale.JAPANESE)
                        .format(zonedDateTime1);

        String dateStr3 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(zonedDateTime1);
        String dateStr4 = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(localDate1);


        // or from a pattern (standardized)
        String weekDayStr1 = DateTimeFormatter.ofPattern("E").format(zonedDateTime1);
        String weekDayStr2 = DateTimeFormatter.ofPattern("EEEE").format(zonedDateTime1);
        String weekDayStr3 = DateTimeFormatter.ofPattern("EEEEE").format(zonedDateTime1);
        String dateStr5 = DateTimeFormatter.ofPattern("uuuu'-'MM'-'dd").format(zonedDateTime1);

        // optional sections
        TemporalAccessor temporalAccessor2 = DateTimeFormatter.ofPattern("HH':'mm[':'ss[':'nnnnn]]").parse("13:06:25");

        // using a DateTimeFormatter builder
        // For example: "2011 Dec 03" can use both methods:

        // method I
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("uuuu' 'MMM' 'dd");
        // method II
        DateTimeFormatter dateTimeFormatter2 =
                new DateTimeFormatterBuilder()
                        .appendValue(ChronoField.YEAR, 4)
                        .appendLiteral(" ")
                        .appendText(ChronoField.MONTH_OF_YEAR, TextStyle.SHORT)
                        .appendLiteral(" ")
                        .appendValue(ChronoField.DAY_OF_MONTH, 2)
                        .toFormatter(Locale.FRANCE);

        String dateStr6 = dateTimeFormatter1.format(zonedDateTime1);
        String dateStr7 = dateTimeFormatter2.format(zonedDateTime2);

        LocalDate localDate2 = dateTimeFormatter2.parse("2011 déc. 03", LocalDate::from);
    }
}
/* PREDEFINED DateTimeFormatter:
        BASIC_ISO_DATE	Basic ISO date	'20111203'
        ISO_LOCAL_DATE	ISO Local Date	'2011-12-03'
        ISO_OFFSET_DATE	ISO Date with offset	'2011-12-03+01:00'
        ISO_DATE	ISO Date with or without offset	'2011-12-03+01:00'; '2011-12-03'
        ISO_LOCAL_TIME	Time without offset	'10:15:30'
        ISO_OFFSET_TIME	Time with offset	'10:15:30+01:00'
        ISO_TIME	Time with or without offset	'10:15:30+01:00'; '10:15:30'
        ISO_LOCAL_DATE_TIME	ISO Local Date and Time	'2011-12-03T10:15:30'
        ISO_OFFSET_DATE_TIME	Date Time with Offset	2011-12-03T10:15:30+01:00'
        ISO_ZONED_DATE_TIME	Zoned Date Time	'2011-12-03T10:15:30+01:00[Europe/Paris]'
        ISO_DATE_TIME	Date and time with ZoneId	'2011-12-03T10:15:30+01:00[Europe/Paris]'
        ISO_ORDINAL_DATE	Year and day of year	'2012-337'
        ISO_WEEK_DATE	Year and Week	2012-W48-6'
        ISO_INSTANT	Date and Time of an Instant	'2011-12-03T10:15:30Z'
        RFC_1123_DATE_TIME	RFC 1123 / RFC 822	'Tue, 3 Jun 2008 11:05:30 GMT'
        */