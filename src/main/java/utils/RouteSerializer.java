package utils;

import console.Debug;
import console.DebugList;
import server.data.Route;
import vue.utils.Props;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class RouteSerializer {

    private static final RouteList LIST_ROUTE = deserializeRoutes();

    private RouteSerializer() {
    }

    public static void serializeRoute() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Props.FILE_SERIALIZE_ROUTE))) {
            oos.writeObject(LIST_ROUTE);
        } catch (IOException e) {
            Debug.print(DebugList.ERROR, "IOException : création de l'objet impossible");
        }
    }

    public static List<Route> getListRoute() {
        return LIST_ROUTE;
    }

    public static void addRoute(Route route) {
        LIST_ROUTE.add(route);
        serializeRoute();
    }

    private static RouteList deserializeRoutes() {
        RouteList routeList = new RouteList();
        try {
            File f = new File(Props.FILE_SERIALIZE_ROUTE);
            if (f.createNewFile()) return routeList;
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                routeList = (RouteList) ois.readObject();
            }
        } catch (IOException ie) {
            Debug.print(DebugList.ERROR, "IOException : conversion de l'objet impossible");
        } catch (ClassNotFoundException e) {
            Debug.print(DebugList.ERROR, "Classe non trouvé");
        }
        return routeList;
    }

    public static class RouteList extends LinkedList<Route> implements Serializable {
        @Serial
        private static final long serialVersionUID = 1L;
    }

}
