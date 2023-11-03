package howto.rounding;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounding {

    public static void main(String[] args) {

        double[] doubleArr = new double[]
                {0, 0.00, 0.000, 10, 10.0, 10.00, 10.000, 10.0000, 100, 1000, 1000.01,
                 12, 12.0, 12.5, 12.2, 12.6, 12.9, 12.10, 12.11, 12.15, 12.16, 12.19,
                12.190, 12.191, 12.195, 12.196, 12.199, 12.1900, 12.1999, 9.9, 9.99, 9.999};

        String[] strArr = new String[]
                {"0", "0.00", "0.000", "10", "10.0", "10.00", "10.000", "10.0000", "100", "1000", "1000.01",
                        "12", "12.0", "12.5", "12.2", "12.6", "12.9", "12.10", "12.11", "12.15", "12.16", "12.19",
                        "12.190", "12.191", "12.195", "12.196", "12.199", "12.1900", "12.1999", "9.9", "9.99", "9.999",
                        "", " ", "d"};

        for (double v : doubleArr) {
            System.out.println(round(v, 2));
        }

        System.out.println("==========");

        for (String v : strArr) {
            System.out.println(v + " : " + round(v, 2));
        }

        System.out.println("==========");

        for (String v : strArr) {
            System.out.println(v + " : " + roundHalfEven(v, 2));
        }
    }

    public static String round(double value, int scale) {
        BigDecimal bd = new BigDecimal(String.valueOf(value));
        return bd.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    /**
     * @param value
     *           received value
     * @param scale
     *           scale needed for the returned value
     * @return a formatted value with the required scale and with trailing zeroes
     *         removed<br>
     *         For example:<br>
     *         "0" returns "0"<br>
     *         "1" returns "1"<br>
     *         "12.5400" returns "12.54" when scale is set to 2
     */
    public static String round(String value, int scale) {
        if (StringUtils.isBlank(value) || !NumberUtils.isNumber(value)) {
            return "0";
        }
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(scale, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }

    public static String roundHalfEven(String value, int scale) {
        if (StringUtils.isBlank(value) || !NumberUtils.isNumber(value)) {
            return "0";
        }
        BigDecimal bd = new BigDecimal(value);
        return bd.setScale(scale, RoundingMode.HALF_EVEN).stripTrailingZeros().toPlainString();
    }

}
