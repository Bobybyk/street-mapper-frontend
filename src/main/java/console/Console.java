package console;

import java.util.HashMap;
import java.util.Scanner;

import client.Client;
import commands.debug.CommandDebug;
import commands.debug.CommandHelp;
import commands.debug.CommandIndexesList;
import commands.debug.CommandKill;
import commands.debug.CommandSettings;
import commands.tcp.RequestIndexesList;
import commands.tcp.RequestTcp;
import commands.tcp.out.RequestTcpRoute;
import commands.tcp.out.RequestTcpSearchStation;
import commands.tcp.out.RequestTcpTimeStation;

public class Console implements Runnable {
    /**
     * objet contenant toutes les méthodes et paramètres nécessaires à la
     * communication avec le serveur
     */
    private final Client client;
    /**
     * pour lire les entrées standard
     */
    private final Scanner sc;
    /**
     * liste des requêtes disponibles, triées par index
     */
    private static final HashMap<String, RequestTcp> requestList;
    /**
     * liste des commandes disponibles, triées par index
     */
    private static final HashMap<String, CommandDebug> commandList;
    /**
     * true si la console est en cours d'exécution, false sinon
     */
    private boolean isRunning;

    static {
        requestList = new HashMap<>();
        commandList = new HashMap<>();

        // initialisation des commandes et requêtes
        requestList.put(RequestIndexesList.ROUTE, new RequestTcpRoute());
        requestList.put(RequestIndexesList.SEARCH, new RequestTcpSearchStation());
        requestList.put(RequestIndexesList.TIME, new RequestTcpTimeStation());
        
        commandList.put(CommandIndexesList.KILL, new CommandKill());
        commandList.put(CommandIndexesList.HELP, new CommandHelp());
        commandList.put(CommandIndexesList.DEBUG, new CommandSettings());
    }

    /**
     * @param client objet contenant toutes les méthodes et paramètres nécessaires à
     *               la communication avec le serveur
     */
    public Console(Client client) {
        this.client = client;
        this.sc = new Scanner(System.in);

        // la console est marquée comme prête à écouter
        this.isRunning = true;

        // affichage de la liste des commandes
        commandList.get(CommandIndexesList.HELP).execute(null, this);
    }

    /**
     * @param index index de la requête
     * @return true si la commande existe, false sinon
     */
    private boolean requestExists(String index) {
        return requestList.containsKey(index);
    }

    private boolean commandExists(String index) {
        return commandList.containsKey(index);
    }

    /**
     * @param command command provenant de l'entrée standard
     * @return la commande segmentées
     */
    private String[] segmentsCommand(String command, String separator) {
        return command.split(separator);
    }

    /**
     * @param command commande provenant de l'entrée standard
     */
    public void handleCommand(String command, String separator) {
        String[] segmentedCommand = segmentsCommand(command, separator);
        String commandIndex = segmentedCommand[0];
        if (requestExists(commandIndex)) {
            if (client != null) {
                String buildedRequest;
                try {
                    buildedRequest = requestList.get(commandIndex).commandBuilder(segmentedCommand);
                    client.setNextRequest(buildedRequest, commandIndex);
                } catch (NumberFormatException e) {
                    Debug.print(DebugList.WARNING, "[WARNING/Console] Arguments invalides pour la requête, elle ne sera pas envoyé");
                } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                    Debug.print(DebugList.WARNING, "[WARNING/Console] Arguments manquants pour la requête, elle ne sera pas envoyé");
                }
            } else {
                Debug.print(DebugList.WARNING, "[WARNING/Console] Aucune connexion au serveur, la requête ne pourra être envoyée");
            }
        } else if (commandExists(commandIndex)) {
            commandList.get(commandIndex).execute(segmentedCommand, this);
        } else {
            Debug.print(DebugList.WARNING, "[WARNING/Console] Commande non définie dans le protocole");
        }
    }

    /**
     * Affiche le layout de la console
     */
    public static void layout() {
        Debug.write("\u001B[31m");
        Debug.write("map_debug> ");
        Debug.write("\u001B[37m");
    }

    @Override
    public void run() {
        while (isRunning) {
            layout();
            handleCommand(sc.nextLine(), " ");
        }
        sc.close();
    }

    /**
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param b indique si le client est encore actif
     */
    public void setRunning(boolean b) {
        isRunning = b;
    }


    public static HashMap<String, CommandDebug> getCommandList() {
        return new HashMap<>(commandList);
    }

    public static HashMap<String, RequestTcp> getRequestList() {
        return new HashMap<>(requestList);
    }
}
