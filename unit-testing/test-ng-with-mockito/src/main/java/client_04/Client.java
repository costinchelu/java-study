package client_04;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private final List<Phone> phones = new ArrayList<Phone>();

    // the Client class is tightly coupled with the Phone class.
    // There is no interface that would shield Phone from Client.
    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    /*
    if the client has no phones, the hasMobile() method returns false,
    if the client has only stationary (i.e. landline) phones, the hasMobile() method returns false,
    if the client has one or more mobile phones (and any number of stationary phones), the hasMobile() method returns true.
    * */
    public boolean hasMobile() {
        for (Phone phone : phones) {
            if (phone.isMobile()) {
                return true;
            }
        }
        return false;
    }
}
