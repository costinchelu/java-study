package chapter4;

import chapter2.WorkPeriod;
import chapter2.WorkPeriods;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class InstantAndOffsetDateTime {

    public static void main(String[] args) {
        /*
        Takeoff -> London 13:15Z
        Landing -> Beijing 17:55+08:00 (next day
         */

        ZoneOffset origZone = ZoneOffset.of("+0");
        ZoneOffset destZone = ZoneOffset.of("+8");
        Clock testClock = Clock.fixed(Instant.EPOCH, ZoneOffset.UTC);

        List<WorkPeriod> wps = WorkPeriods.generateWorkPeriods(LocalDate.now(testClock), 2);

        // Local time in Beijing at landing
        LocalDateTime landingLocalTime =
                LocalDateTime.of(LocalDate.now(testClock).plusDays(1), LocalTime.of(17, 55));

        // Local time in Beijing at landing with +08:00 offset
        OffsetDateTime landingWithDestinationOffset = OffsetDateTime.of(landingLocalTime, destZone);
        // Local time in London at landing with Z offset
        OffsetDateTime landingWithOriginOffset = landingWithDestinationOffset.withOffsetSameInstant(origZone);
        // Local time in London at landing (no offset)
        LocalDateTime originLocalLandingTime = landingWithOriginOffset.toLocalDateTime();

        //  2nd method
        int zoneDifferenceSeconds = destZone.getTotalSeconds() - origZone.getTotalSeconds();
        originLocalLandingTime = landingLocalTime.minusSeconds(zoneDifferenceSeconds);

        List<WorkPeriod> usableWorkPeriods = new ArrayList<>();
        for (WorkPeriod wp : wps) {
            System.out.print(wp + "\t" + (!wp.contains(originLocalLandingTime)));
        }
        System.out.println();

        // ZoneOffset vs ZoneId

        // ZoneOffset uses signed numerals for zones
        System.out.println("ZoneOffset for London is " + ZoneOffset.of("Z"));
        System.out.println("ZoneOffset for Bucharest is " + ZoneOffset.of("+03:00"));

        // ZoneId uses a string value represented by Continent/City or "Etc/GMT<signed integer>
        System.out.println("Local ZoneId is " + ZoneId.systemDefault());
        System.out.println("Local zone rules: " + ZoneId.systemDefault().getRules());

        // daylight saving time infos:
        System.out.println("Local zone transition rules: ");
        ZoneId.systemDefault().getRules().getTransitionRules().forEach(System.out::println);

        // ZonedDateTime
        // wrapper for LocalDateTime
        // take into account daylight saving time
        LocalDateTime localDateTime
                = LocalDateTime.of(2021, 3, 28, 1, 30);
        ZoneId zoneIdAthens = ZoneId.of("Europe/Athens");
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, zoneIdAthens);
    }
}
