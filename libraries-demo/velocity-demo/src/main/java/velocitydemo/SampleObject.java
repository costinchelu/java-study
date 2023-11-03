package velocitydemo;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SampleObject {

    private String errorDescription;

    private String actual;

    private String expected;
}
