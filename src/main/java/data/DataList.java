package data;

import server.data.ServerResponse;

public class DataList {
    /**
     * informations relativent à la derniere requete reçu du serveur
     */
    public static ServerResponse data = null;

    private DataList() {}

    public static Serializable getData() {
        return data;
    }

    public static void setData(Serializable data) {
        DataList.data = data;
    }
}
