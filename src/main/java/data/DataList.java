package data;

import server.data.ServerResponse;

public class DataList {
    /**
     * informations relativent à la derniere requete reçu du serveur
     */
    private static ServerResponse data = null;

    private DataList() {}

    public static ServerResponse getData() {
        return data;
    }

    public static void setData(ServerResponse data) {
        DataList.data = data;
    }
}
