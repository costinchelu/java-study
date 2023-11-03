package brianvermeer.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Beer {
    String name;
    Double alcohol;

    public Beer withName(String name) {
        return new Beer(name, this.alcohol);
    }




}
