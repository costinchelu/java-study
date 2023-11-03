package dp_creational.factory.calendarexample;

import java.util.Calendar;

public class CalendarClassExample {

    public static void main(String[] args) {

        Calendar cal = Calendar.getInstance();
        System.out.println(cal);
        System.out.println(cal.get(Calendar.DAY_OF_MONTH));
    }
}
/*
A factory method implementation in Calendar class
 */