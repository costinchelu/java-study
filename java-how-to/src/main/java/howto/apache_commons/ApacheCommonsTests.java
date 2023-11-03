package howto.apache_commons;


import static org.apache.commons.lang3.math.NumberUtils.*;

public class ApacheCommonsTests {

    public static void main(String[] args) {

        System.out.println(isCreatable("0"));
        System.out.println(isCreatable("0.1"));
        System.out.println(isCreatable("1.6"));
        System.out.println(isCreatable("245.21145"));
        System.out.println(isCreatable("0.0"));
        System.out.println(isCreatable("1.0"));
        System.out.println(isCreatable(""));
        System.out.println(isCreatable(" "));
        System.out.println(isCreatable("l"));
    }
}
