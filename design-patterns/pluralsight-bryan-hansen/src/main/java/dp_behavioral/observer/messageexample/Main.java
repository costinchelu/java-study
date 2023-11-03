package dp_behavioral.observer.messageexample;


import dp_behavioral.observer.messageexample.concreteobserver.PhoneClient;
import dp_behavioral.observer.messageexample.concreteobserver.TabletClient;
import dp_behavioral.observer.messageexample.concretesubject.MessageStream;
import dp_behavioral.observer.messageexample.subjectbase.Subject;

public class Main {

    public static void main(String[] args) {

        Subject subject = new MessageStream();

        PhoneClient phoneClient = new PhoneClient(subject);
        TabletClient tableClient = new TabletClient(subject);

        phoneClient.addMessage("Hello!");
        tableClient.addMessage("How are you?");
    }
}
