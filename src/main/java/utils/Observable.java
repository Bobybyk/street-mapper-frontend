package utils;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
