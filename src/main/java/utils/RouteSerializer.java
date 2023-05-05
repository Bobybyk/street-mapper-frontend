package utils;

import console.Debug;
import console.DebugList;
import server.data.Route;
import vue.utils.Props;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class RouteSerializer {

    private static final LinkedList<Route> LIST_ROUTE = deserializeRoutes();

    private RouteSerializer(){}

    public static void serializeRoute() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Props.FILE_SERIALIZE_ROUTE));
            oos.writeObject(LIST_ROUTE);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Route> getListRoute() {
        return LIST_ROUTE;
    }

    public static void addRoute(Route route) {
        LIST_ROUTE.add(route);
        serializeRoute();
    }

    private static LinkedList<Route> deserializeRoutes() {
        LinkedList<Route> routeList = new LinkedList<>();
        try {
            File f = new File(Props.FILE_SERIALIZE_ROUTE);
            if (f.createNewFile()) return routeList;
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            routeList = (LinkedList<Route>) ois.readObject();
            ois.close();
        } catch (IOException ie) {
            Debug.print(DebugList.ERROR, "IOException : conversion de l'object impossible");
        } catch (ClassNotFoundException e){
            Debug.print(DebugList.ERROR, "Classe non trouvé");
        }
        return routeList;
    }
}
