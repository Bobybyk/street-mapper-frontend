package vue.panel;

import utils.Observable;
import utils.Observer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ResearchPanel extends JPanel implements Observable {


    private List<Observer> listObserver =  new ArrayList<>();

    public ResearchPanel(){
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

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
