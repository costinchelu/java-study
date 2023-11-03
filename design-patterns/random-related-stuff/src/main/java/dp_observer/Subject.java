package dp_observer;

interface Subject {

   void registerObserver(Observer o);

   void notifyObservers(String tweet);
}
