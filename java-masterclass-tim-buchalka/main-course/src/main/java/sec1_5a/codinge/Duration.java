package sec1_5a.codinge;

public class Duration
{
    private static final String INVALID_VALUE_MESSAGE = "Invalid value";		//atribut constant


    public static String getDurationString(int minutes, int seconds)
    {
        if (minutes >= 0 && (seconds >=0 && seconds <=59))
        {
            int hours = minutes / 60;
            minutes %= 60;

            String hoursString = hours + "h ";
            if (hours < 10) hoursString = "0" + hoursString;

            String minutesString = minutes + "m ";
            if (minutes < 10) minutesString =  "0" + minutesString;

            String secondsString = seconds + "h";
            if (seconds < 10) secondsString =  "0" + secondsString;

            return hoursString + minutesString + secondsString;
        }
        return INVALID_VALUE_MESSAGE;
    }


    public static String getDurationString(int seconds)
    {
        if (seconds > 0)
        {
            int minutes = seconds / 60;
            seconds %= 60;
            return getDurationString(minutes, seconds);
        }
        return INVALID_VALUE_MESSAGE;
    }
}
