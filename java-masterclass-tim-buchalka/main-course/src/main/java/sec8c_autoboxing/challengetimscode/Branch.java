package sec8c_autoboxing.challengetimscode;


import java.util.ArrayList;

public class Branch {
    private String name;        //branch name
    private ArrayList<Customer> customers;

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<Customer>();
    }

    public ArrayList<Customer> getCustomers() { return customers; }

    public String getName() { return name; }

    public boolean newCustomer(String customerName, double initalAmount) {
        if(findCustomer(customerName) == null) {
            this.customers.add(new Customer(customerName, initalAmount));
            return true;
        }
        return false; //return false if customer already exists
    }

    public boolean addCustomerTransaction(String customerName, double amount) {
        Customer existingCustomer = findCustomer(customerName);     //check if customer exists
        if(existingCustomer != null) {
            existingCustomer.addTransaction(amount);        //if customer exists, add a new transaction
            return true;
        }
        return false;       //with no customer, no transaction is added
    }

    private Customer findCustomer(String customerName) {
        for(int i = 0; i < customers.size(); i++) {
            Customer checkedCustomer = this.customers.get(i);
            if(checkedCustomer.getName().equals(customerName)) {
                return checkedCustomer;
            }
        }
        return null;
    }



}

