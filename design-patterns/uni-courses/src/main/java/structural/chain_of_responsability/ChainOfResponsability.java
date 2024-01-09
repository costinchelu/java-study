package structural.chain_of_responsability;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
class Client {

        private String name;

        private String sms;

        private String mail;
}

@Getter
@Setter
abstract class Notifier {

    private Notifier next;

    public abstract void notify(Client client, String message);
}

class EmailNotifier extends Notifier {

    @Override
    public void notify(Client client, String message) {
        if (client.getMail() != null) {
            System.out.println(client.getName() + " you have an email: " + message);
        } else {
            super.getNext().notify(client, message);
        }
    }
}

class MessageNotifier extends Notifier {

    @Override
    public void notify(Client client, String message) {
        if (client.getSms() != null) {
            System.out.println(client.getName() + " you have a message: " + client.getSms());
        } else {
            super.getNext().notify(client, message);
        }
    }
}

class DefaultNotifier extends Notifier {

    @Override
    public void notify(Client client, String message) {
        System.out.println("Contact data missing for: " + client.getName());
    }
}


public class ChainOfResponsability {

    public static void main(String[] args) {
        Notifier email = new EmailNotifier();
        Notifier sms = new MessageNotifier();
        Notifier def = new DefaultNotifier();
        email.setNext(sms);
        sms.setNext(def);

        Client client1 = new Client("Popescu", null, null);
        Client client2 = new Client("Ionescu", "0700000000", null);
        Client client3 = new Client("Georgescu", null, "g@gg.com");

        email.notify(client1, "<<Message P>>");
        email.notify(client2, "<<Message I>>");
        email.notify(client3, "<<Message G>>");
    }
}
