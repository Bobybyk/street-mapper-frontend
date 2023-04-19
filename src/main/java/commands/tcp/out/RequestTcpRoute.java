package commands.tcp.out;

import java.text.DateFormat;
import java.text.ParseException;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpRoute implements RequestTcp {

    public RequestTcpRoute() {

    }

    /**
     * @param date la date à tester
     * @return true si date est au bon format, false sinon
     */
    private boolean isDateFormat(String date) {
        try {
            DateFormat.getDateInstance().parse(date);
            return true;
        } catch (ParseException e) {
            System.out.println("La date n'est pas au bon format");
            return false;
        }
    }

    /**
     * @param args arguments de la requête
     * @return la requête à envoyer au serveur
     */
    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException {
        if (args.length == 3) {
            return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2];
        } else if (isDateFormat(args[3])) {
            if (args.length == 5) {
                return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2] + ";" + args[3] + ";" + args[4];
            } else {
                return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2] + ";" + args[3];
            }
        } else {
            return RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2];
        }
    }

}
