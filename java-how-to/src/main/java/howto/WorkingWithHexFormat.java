package howto;

import java.util.Arrays;
import java.util.HexFormat;

public class WorkingWithHexFormat {

    public static void main(String[] args) {
        HexFormat format = java.util.HexFormat.of();

        byte[] input = new byte[] {127, 0, -50, 105};
        String hex = format.formatHex(input);
        System.out.println(hex);

        byte[] output = format.parseHex(hex);
        if (Arrays.compare(input, output) == 0) {
            System.out.println("input == output");
        }
    }
}
