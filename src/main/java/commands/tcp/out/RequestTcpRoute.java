package commands.tcp.out;

import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;

public class RequestTcpRoute implements RequestTcp {

    public RequestTcpRoute() {
        // Doit etre construit pour appeler commandBuilder
    }

    /**
     * construit la requête à envoyer au serveur et s'assure que les paramètres passés en arguments sont conformes
     *
     * @param args arguments de la requête
     * @return la requête à envoyer au serveur
     */
    @Override
    public String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException, IllegalArgumentException {
        if (args.length > 6) throw new ArrayIndexOutOfBoundsException();
        if (isTimeFormat(args[3])) {
            String request = RequestIndexesList.ROUTE + ";" + args[1] + ";" + args[2] + ";" + args[3] + ";";
            request += (args[4].equals("DISTANCE")) ? "DISTANCE" : "TEMPS";
            request += ";";
            request += (args.length == 6 && args[5].equals("FOOT")) ? "FOOT" : "";
            return request;
        } else {
            throw new IllegalArgumentException("Commande invalide");
        }
    }

}
