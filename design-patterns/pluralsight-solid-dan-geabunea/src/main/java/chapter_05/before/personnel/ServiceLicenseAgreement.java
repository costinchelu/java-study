package chapter_05.before.personnel;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ServiceLicenseAgreement {

    private int minUptimePercent;

    private int problemResolutionTimeDays;
}
