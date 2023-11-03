package howto.model;

import java.util.List;

public class CustomerDatabase {

    public static List<Customer> getAll() {
        return List.of(
            new Customer(1, "John Doe", "john", List.of("758", "246")),
            new Customer(2, "Jane Doe", "jane", List.of("458", "225")),
            new Customer(3, "Brad Smith", "brad", List.of("123", "456"))
        );
    }
}
