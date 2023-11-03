package chapter2;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * <table border="1px" cellspacing="0" cellpadding="10px">
 *  <thead>
 *      <tr>
 *          <th>ISO8601</th>
 *      </tr>
 *  </thead>
 *  <tbody>
 *      <tr>
 *          <td>Date</td>
 *          <td>2021-08-18</td>
 *      </tr>
 *      <tr>
 *          <td>Date and time in UTC</td>
 *          <td>2021-08-18T14:07:49Z</td>
 *      </tr>
 *      <tr>
 *          <td>Date and time in UTC</td>
 *          <td>2021-08-18T14:07:49+00:00</td>
 *      </tr>
 *      <tr>
 *          <td>Date and time in UTC</td>
 *          <td>20210818T140749Z</td>
 *      </tr>
 *      <tr>
 *          <td>Local   (UTC +3)</td>
 *          <td>2021-08-18T17:07:49+03:00</td>
 *      </tr>
 *      <tr>
 *          <td>New York   (UTC -5)</td>
 *          <td>2021-08-18T17:07:49-05:00</td>
 *      </tr>
 *      <tr>
 *          <td>Week</td>
 *          <td>2021-W33</td>
 *      </tr>
 *      <tr>
 *          <td>Week with weekday</td>
 *          <td>2021-W33-3</td>
 *      </tr>
 *      <tr>
 *          <td>Date without year</td>
 *          <td>--08-18</td>
 *      </tr>
 *      <tr>
 *          <td>Ordinal date</td>
 *          <td>2021-230</td>
 *      </tr>
 *  </tbody>
 * </table>
 */
public class Theory {

    public static void main(String[] args) {

        long jan011970 = Instant.EPOCH.getNano();
        System.out.println(jan011970);

        long apr261970 = Instant.ofEpochSecond(9961199).getEpochSecond();
        System.out.println(apr261970);

        long duration = Duration.between(Instant.EPOCH, Instant.ofEpochSecond(9961199)).toDays();
        System.out.println(duration);

        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        System.out.println(ZoneOffset.systemDefault());
        System.out.println(ZonedDateTime.now());

        System.out.println(Duration.ofDays(116).addTo(LocalDateTime.now()));
        System.out.println(Period.of(0, 0, 116).addTo(ZonedDateTime.now()));
    }
}
