package howto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.stream.Stream;

public class CheckForEmptyFields {

    public static void main(String[] args) {
        SomeObject empty = new SomeObject();
        SomeObject notEmpty = new SomeObject();
        notEmpty.setId(12L);
        SomeObject allFields = new SomeObject(14L, "Some details", "Other stuff...");

        boolean result1 = isFieldEmpty(empty);
        boolean result2 = isFieldEmpty(notEmpty);
        boolean result3 = isFieldEmpty(allFields);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }

    public static boolean isFieldEmpty(SomeObject request) {
        return Stream.of(request.getId(), request.getDetails(), request.getOther()).anyMatch(Objects::isNull);
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Data
class SomeObject {

    private long id;

    private String details;

    private String other;
}
