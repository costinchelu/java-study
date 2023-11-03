package howto.rounding;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounding2 {

    public static void main(String[] args) {

        DataObject do1 = DataObject.builder()
                .saleAm(BigDecimal.valueOf(100.00))
                .refundAm(BigDecimal.valueOf(52.16))
                .voidAm(BigDecimal.valueOf(12.28))
                .fee(BigDecimal.valueOf(22.22))
                .build();

        var bd1 = do1.getResult();
        var bd11 = bd1.setScale(2, RoundingMode.HALF_UP);

        DataObject do2 = DataObject.builder()
                .saleAm(BigDecimal.valueOf(4.09))
                .refundAm(BigDecimal.valueOf(1.00))
                .voidAm(BigDecimal.valueOf(2.00))
                .fee(BigDecimal.valueOf(1.00))
                .build();

        var bd2 = do2.getResult();
        var bd21 = bd2.setScale(2, RoundingMode.HALF_UP);
    }
}
