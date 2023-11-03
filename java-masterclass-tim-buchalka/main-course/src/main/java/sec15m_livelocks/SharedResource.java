package sec15m_livelocks;

public class SharedResource {
    private Worker owner;

    public SharedResource(Worker owner) {
        this.owner = owner;
    }

    public Worker getOwner() {
        return owner;
    }

    //setter needs to be synchronized because we want to change some data (no thread interference)
    public synchronized void setOwner(Worker owner) {
        this.owner = owner;
    }

}
