package mutex;

import lombok.Getter;
import lombok.Setter;

public class LinkEdList {

    @Getter
    private ListElement rootElement;

    private ListElement lastElement;

    private int currentIndex;

    public LinkEdList() {
        this.rootElement = null;
        this.lastElement = null;
        this.currentIndex = 0;
    }

    public synchronized void add(String message) {
        ListElement element = new ListElement(message, this.currentIndex);
        if (rootElement == null) {
            rootElement = element;
            lastElement = element;
        } else {
            lastElement.setNext(element);
            lastElement = lastElement.getNext();
        }
        this.currentIndex++;
    }

    public void printList() {
        ListElement current = rootElement;
        while (current != null) {
            System.out.println(current.getCurrentIndex() + " : " + current.getMessage());
            current = current.getNext();
        }
    }
}



@Getter
@Setter
class ListElement {

    private String message;

    private int currentIndex;

    private ListElement next;

    public ListElement(String message, int currentIndex) {
        this.message = message;
        this.next = null;
        this.currentIndex = currentIndex;
    }
}