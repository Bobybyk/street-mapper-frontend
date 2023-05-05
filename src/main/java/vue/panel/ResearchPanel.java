package vue.panel;

import utils.Observable;
import utils.Observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ResearchPanel est une classe Jpanel generique elle est observée et mise à jour automatiquement
 * par l'observeur en cas de retour de requete
 */
public class ResearchPanel extends JPanel implements Observable {

    private final List<Observer> listObserver =  new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listObserver) {
            observer.update(this);
        }
    }

}
