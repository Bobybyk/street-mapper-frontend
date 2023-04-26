package commands.tcp.out;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;
import console.Debug;
import console.DebugList;

public class RequestTcpRoute implements RequestTcp {

    public RequestTcpRoute() {

    }

    /**
     * teste si la date passée en argument est un format accepté par l'objet DateFormat
     * @param date la date à tester
     * @return true si date est au bon format, false sinon
     */
    private boolean isTimeFormat(String date) {
        int[] time = new int[2];
        try {
            time[0] = Integer.parseInt(date.substring(0, 1));
            time[1] = Integer.parseInt(date.substring(3, 4));
            if (date.charAt(3) == ':')
                return true;
        } catch (NumberFormatException e) {
            Debug.print(DebugList.WARNING, "[WARNING/RequestTcpRoute] La date renseignée n'est pas au bon format");
        }
        return false;
    }

    /**
     * construit la requête à envoyer au serveur et s'assure que les paramètres passés en arguments sont conformes
     * @param args arguments de la requête
     * @return la requête à envoyer au serveur
     */
    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException {
        if (args.length == 3) {
            return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2];
        } else if (isTimeFormat(args[3])) {
            if (args.length == 5 && (args[4].equals("DISTANCE") || args[4].equals("TIME") || args[4].equals("FOOT"))) {
                return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2] + ";" + args[3] + ";" + args[4];
            } else {
                return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2] + ";" + args[3];
            }
        } else {
            return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2];
        }
    }

}
