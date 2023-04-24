package data;

import java.io.Serializable;

public class DataList {
    /**
     * informations relativent au dernier trajet reçu du serveur, object Route
     */
    public static Serializable route = null;
    /**
     * informations relativent à la dernière station proposée par le serveur, object SuggestionStations
     */
    public static Serializable station = null;
    /**
     * informations relativent au dernier temps d'attente reçu du serveur, object TimeStation
     */
    public static Serializable timeStation = null;

}
