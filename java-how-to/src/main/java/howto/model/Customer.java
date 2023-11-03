package howto.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Customer {

    private long id;

    private String name;

    private String email;

    private List<String> metadata;
}
