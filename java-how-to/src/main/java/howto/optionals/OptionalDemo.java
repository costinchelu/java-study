package howto.optionals;

import howto.model.Customer;
import howto.model.CustomerDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        Customer customer =
                new Customer(101, "john", null, Arrays.asList("397937955", "21654725"));


        Optional<Object> emptyOptional = Optional.empty();
        System.out.println(emptyOptional);

        Optional<String> emailOptional = Optional.ofNullable(customer.getEmail());
        Optional<String> nameOptional = Optional.ofNullable(customer.getName());


        System.out.println(emailOptional.orElse("default email"));
        System.out.println(nameOptional.orElse("default name"));

        try {
            System.out.println(emailOptional.orElseThrow(() -> new IllegalArgumentException("No email")));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(emailOptional.map(String::toUpperCase).orElseGet(() -> "Default email..."));
        System.out.println(nameOptional.map(String::toUpperCase).orElseGet(() -> "No name defined..."));

        try {
            Customer c = getCustomerByEmailId("brad");
            System.out.println(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Customer getCustomerByEmailId(String email) throws Exception {
        List<Customer> customers = CustomerDatabase.getAll();
        return customers.stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findAny().orElseThrow(() -> new Exception("Can't find any entry with provided id"));
    }
}
