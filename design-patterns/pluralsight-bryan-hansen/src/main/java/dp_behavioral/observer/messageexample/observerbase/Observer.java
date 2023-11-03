package dp_behavioral.observer.messageexample.observerbase;


import dp_behavioral.observer.messageexample.subjectbase.Subject;

public abstract class Observer {

    protected Subject subject;

    public abstract void update();
}
