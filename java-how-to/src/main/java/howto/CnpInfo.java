package howto;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public record CnpInfo(String CNP) {

    public String getSex() {
        return switch (CNP.charAt(0)) {
            case '1', '3', '5' -> "M";
            case '2', '4', '6' -> "F";
            default -> "N/A";
        };
    }

    public int getAge() {
        int year = 0;
        int yearChars = Integer.parseInt("" + CNP.charAt(1) + CNP.charAt(2));
        if (CNP.charAt(0) == '1' || CNP.charAt(0) == '2') {
            year = (1900 + yearChars);
        } else if (CNP.charAt(0) == '3' || CNP.charAt(0) == '4') {
            year = (1800 + yearChars);
        } else if (CNP.charAt(0) == '5' || CNP.charAt(0) == '6') {
            year = (2000 + yearChars);
        }

        int month = Integer.parseInt("" + CNP.charAt(3) + CNP.charAt(4));

        int day = Integer.parseInt("" + CNP.charAt(5) + CNP.charAt(6));

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(year, month, day);
        Calendar currentDate = Calendar.getInstance();
        long dayAge = TimeUnit.MILLISECONDS.toDays(Math.abs(currentDate.getTimeInMillis() - birthDate.getTimeInMillis()));
        return (int) (dayAge / 365);
    }

    public boolean checkCNP() {
        int s = 0;
        String standard = "279146358279";
        for (int i = 0; i < 12; i++) {
            s += Integer.parseInt("" + standard.charAt(i)) * Integer.parseInt("" + CNP.charAt(i));
        }
        int figure = s % 11;
        if (figure == 10)
            figure = 1;
        return figure == Integer.parseInt("" + CNP.charAt(12));
    }
}
