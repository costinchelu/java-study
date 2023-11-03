package howto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

public class IdentityVsEquality {

    public static void main(String[] args) {

        Human human1 = new Human("John");
        Human human2 = new Human("John");

        boolean identical = human1.equals(human2);

        System.out.println("EQUALITY: " + identical);

        System.out.println("IDENTITY: " + (human1 == human2));

        int a = human1.hashCode();
        int b = human2.hashCode();
        System.out.println("Hashcodes: " + a + "    " + b);
    }
}

@RequiredArgsConstructor
@Getter
class Human {

    private final String name;

// override equals for the particular case of Human class
    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        return Objects.equals(this.getName(), ((Human) obj).getName());
    }
}
