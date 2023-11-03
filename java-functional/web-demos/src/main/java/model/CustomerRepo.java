package model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class CustomerRepo {

    private final List<Customer> customers = new ArrayList<>();

    public CustomerRepo() {
        customers.add(new Customer(1, "John"));
        customers.add(new Customer(2, "Emily"));
        customers.add(new Customer(3, "Brad"));
        customers.add(new Customer(4, "Sara"));
        customers.add(new Customer(5, "Jack"));
        customers.add(new Customer(6, "Jill"));
        customers.add(new Customer(7, "Tod"));
        customers.add(new Customer(8, "Mat"));
        customers.add(new Customer(9, "Clara"));
    }

    public List<Optional<Customer>> findById(long id) {
        return List.of(customers.stream().filter(c -> c.getId() == id).findFirst());
    }
}
