package data;

import java.io.Serializable;

public class DataList {
    /**
     * informations relativent à la derniere requete reçu du serveur
     */
    private static Serializable data = null;

    public static Serializable getData() {
        return data;
    }

    public static void setData(Serializable data) {
        DataList.data = data;
    }
}
