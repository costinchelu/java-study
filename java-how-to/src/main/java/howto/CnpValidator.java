package howto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Validator for Romanian CNP format
 */
public class CnpValidator {
    private static final String STD_VALUE = "2791463582790";

    public static boolean validareCnp(String cnp) {
        if (cnp.length() != 13) {
            return false;
        }
        List<Integer> cnpSplit = Arrays.stream(cnp.split("")).map(Integer::parseInt).toList();
        List<Integer> valueSplit = Arrays.stream(STD_VALUE.split("")).map(Integer::parseInt).toList();
        int sum = 0;
        int i = 0;
        for (Integer nr : cnpSplit) {
            sum += nr * valueSplit.get(i);
            i++;
        }
        int rest = sum % 11;
        if (rest == 10 && cnpSplit.get(12) != 1) {
            return false;
        } else return rest == cnpSplit.get(12);
    }

}
