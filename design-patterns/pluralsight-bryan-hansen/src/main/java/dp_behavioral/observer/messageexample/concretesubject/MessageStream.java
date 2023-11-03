package dp_behavioral.observer.messageexample.concretesubject;


import dp_behavioral.observer.messageexample.subjectbase.Subject;

import java.util.ArrayDeque;
import java.util.Deque;

public class MessageStream extends Subject {

    private Deque<String> messageHistory = new ArrayDeque<>();

    @Override
    public void setState(String message) {
        messageHistory.add(message);
        this.notifyObservers();
    }

    @Override
    public String getState() {
        return messageHistory.getLast();
    }
}
