package howto.rounding;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DataObject {

    BigDecimal saleAm;
    BigDecimal voidAm;
    BigDecimal refundAm;
    BigDecimal fee;

    public BigDecimal getResult() {
        return saleAm
                .subtract(refundAm)
                .subtract(voidAm)
                .subtract(fee);
    }
}
