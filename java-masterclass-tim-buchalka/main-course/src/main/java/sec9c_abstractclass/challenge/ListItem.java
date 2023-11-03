package sec9c_abstractclass.challenge;


public abstract class ListItem {
    protected ListItem rightLink = null;
    protected ListItem leftLink= null;
    protected Object value;

    public ListItem(Object value) {
        this.value = value;
    }



    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    //we need next and previous as a getter and setter (abstract methods)
    abstract ListItem next();
    abstract ListItem setNext(ListItem item);
    abstract ListItem previous();
    abstract ListItem setPrevious(ListItem item);

    //in-depth comparison:
    abstract int compareTo(ListItem item);
}
