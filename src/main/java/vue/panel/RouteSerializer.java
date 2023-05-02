package vue.panel;

import app.server.data.Route;
import console.Debug;
import console.DebugList;
import vue.utils.Props;

import java.io.*;
import java.util.LinkedList;

public class RouteSerializer {

    private static final LinkedList<Route> listRoute = deserializeRoutes();

    public static void serializeRoute() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Props.fileSerializeRoute));
            oos.writeObject(listRoute);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static LinkedList<Route> getListRoute() {
        return listRoute;
    }

    public static void addRoute(Route route) {
        listRoute.add(route);
        serializeRoute();
    }

    private static LinkedList<Route> deserializeRoutes() {
        LinkedList<Route> routeList = new LinkedList<>();
        try {
            File f = new File(Props.fileSerializeRoute);
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
