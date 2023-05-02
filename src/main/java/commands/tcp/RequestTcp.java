package commands.tcp;

import console.Debug;
import console.DebugList;

public interface RequestTcp {

    /**
     * @param args arguments de la requête
     * @return la requête construite pour le serveur
     */
    String commandBuilder(String[] args) throws ArrayIndexOutOfBoundsException;

    /**
     * teste si la date passée en argument est un format accepté par l'objet DateFormat
     *
     * @param date la date à tester
     * @return true si date est au bon format, false sinon
     */
    default boolean isTimeFormat(String date) {
        try {
            Integer.parseInt(date.substring(0, 2));
            Integer.parseInt(date.substring(3, 5));
            return date.charAt(2) == ':';
        } catch (NumberFormatException e) {
            Debug.print(DebugList.WARNING, "[WARNING/RequestTcpRoute] La date renseignée n'est pas au bon format");
        }
        return false;
    }
}
