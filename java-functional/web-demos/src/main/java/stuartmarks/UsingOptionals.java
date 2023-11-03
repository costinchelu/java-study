package stuartmarks;


import model.Customer;
import model.CustomerRepo;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;


public class UsingOptionals {

    public static void main(String[] args) {
        CustomerRepo repo = new CustomerRepo();
        List<Customer> customers = repo.getCustomers();

        try {
            String result = customerNameById(customers, 10);
            System.out.println(result);
        } catch (CustomCustomerException e) {
            e.printStackTrace();
        }


        ifCustomerNameById(customers, 9);
        ifCustomerNameById(customers, 10);


        System.out.println(Optional.of(customers.get(5)).equals(returnOptional(customers, 6)));
        System.out.println(Optional.empty().equals(returnOptional(customers, 10)));


        List<Customer> get1 = getSomething(repo, 5);
        List<Customer> get2 = getSomething(repo, 10);
    }

    private static List<Customer> getSomething(CustomerRepo repo, long id) {
        return repo.findById(id).stream()
                .filter(Optional::isPresent) /* let only present Optionals*/
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    /*
    orElse(default) = returns the value if present or else returns a default value

    orElseGet(supplier) = returns a value if present or else gets a default value calling a supplier

    orElseThrow(exSupplier) = returns a value if present or else throws an exception obtained from a supplier
     */
    private static String customerNameById(List<Customer> customers, int customerId) throws CustomCustomerException {
        return customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .map(Customer::getName)
                .orElse("UNKNOWN");
//                .orElseGet(getDefaultCustomer());
//                .orElseThrow(CustomCustomerException::new);
    }

    private static void ifCustomerNameById(List<Customer> customers, int customerId) {
        customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .map(Customer::getName)
                .ifPresent(System.out::println);
    }

    private static Optional<Customer> returnOptional(List<Customer> customers, int customerId) {
        return customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst();
    }

    private static Supplier<String> getDefaultCustomer() {
        return () -> "UNKNOWN";
    }
}

class CustomCustomerException extends Exception {

}
