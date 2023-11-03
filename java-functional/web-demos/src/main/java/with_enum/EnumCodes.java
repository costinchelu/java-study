package with_enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Getter
public enum EnumCodes {

    CODE1("1"),
    CODE2("2"),
    CODE3("3");

    private final String enumValue;

    public static EnumCodes getCodeName(String codeVal) {
        return Stream.of(values()).filter((enumCodes) -> codeVal.equals(enumCodes.getEnumValue())).findFirst().orElse(null);
    }
}