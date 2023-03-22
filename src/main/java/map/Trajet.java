package map;

import java.io.Serializable;
import java.util.ArrayList;

public class Trajet implements Serializable {

    private final ArrayList<String> list;
    private final String depart, arrive;

    public Trajet(){
        this.depart = "A";
        this.arrive = "E";
        list = new ArrayList<>();
        list.add("STATION A ");
        list.add("STATION B ");
        list.add("STATION C ");
        list.add("STATION D ");
        list.add("STATION E ");

    }

    public String getDepart() {
        return depart;
    }

    public ArrayList<String> getTrajet() {
        return list;
    }

    public String getArrive() {
        return arrive;
    }
}
