package client_address;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private List<Address> addresses;

    public Client() {
        addresses = new ArrayList<>();
    }

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
