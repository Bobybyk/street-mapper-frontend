package console;

import java.util.HashMap;
import java.util.Scanner;

import client.Client;
import commands.debug.CommandDebug;
import commands.debug.CommandHelp;
import commands.debug.CommandIndexesList;
import commands.debug.CommandKill;
import commands.tcp.RequestTcp;
import commands.tcp.RequestIndexesList;
import commands.tcp.out.RequestTcpRoute;

public class Console extends Thread {
    /**
     * objet contenant toutes les méthodes et paramètres nécessaires à la communication avec le serveur
     */
    private Client client;
    /**
     * pour lire les entrées standard
     */
    private Scanner sc;
    /**
     * liste des requêtes disponibles, triées par index
     */
    private HashMap<String, RequestTcp> requestList;
    /**
     * liste des commandes disponibles, triées par index
     */
    private HashMap<String, CommandDebug> commandList;
    /**
     * true si la console est en cours d'exécution, false sinon
     */
    private boolean isRunning;
    

    /**
     * @param client objet contenant toutes les méthodes et paramètres nécessaires à la communication avec le serveur
     */
    public Console(Client client) {
        this.client = client;
        this.sc = new Scanner(System.in);
        this.requestList = new HashMap<String, RequestTcp>();
        this.commandList = new HashMap<String, CommandDebug>();

        // initialisation des commandes et requêtes
        this.requestList.put(RequestIndexesList.ROUTE, new RequestTcpRoute());
        this.commandList.put(CommandIndexesList.KILL, new CommandKill());
        this.commandList.put(CommandIndexesList.HELP, new CommandHelp());

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
    private String[] segmentsCommand(String command) {
        String[] args = command.split(" ");
		return args;
	}

    /**
     * @param command commande provenant de l'entrée standard
     */
    private void handleCommand(String command) {
        String[] segmentedCommand = segmentsCommand(command);
        String commandIndex = segmentedCommand[0];
        if(requestExists(commandIndex)) {
            if (client != null) {
                String buildedRequest;
                try {
                    buildedRequest = requestList.get(commandIndex).commandBuilder(segmentedCommand);
                    client.setNextRequest(buildedRequest, commandIndex);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Arguments manquants pour la requête");
                    e.printStackTrace();
                }
            } else {
                System.out.println("Aucune connexion au serveur");
            }
        } 
        else if (commandExists(commandIndex)) {
            commandList.get(commandIndex).execute(segmentedCommand, this);
        } else {
            System.out.println("Commande non définie dans le protocole");
        }
    }

    /**
     * affiche le layout de la console
     */
    public static void layout() {
        System.out.print("\u001B[31m");
        System.out.print("map_debug> ");
        System.out.print("\u001B[37m");
    }

    @Override
    public void run() {
        while(isRunning) {
            layout();
            handleCommand(sc.nextLine());
        }
    }

    /**
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     * @param b
     */
    public void setRunning(boolean b) {
        isRunning = b;
    }

}
