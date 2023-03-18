package console;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import client.Client;
import commands.debug.CommandDebug;
import commands.debug.CommandKill;
import commands.tcp.RequestTcp;
import commands.tcp.out.RequestTcpRoute;

public class Console extends Thread {
    private Client client;
    private Scanner sc;
    private LinkedList<String> RequestListIndexes;
    private HashMap<String, RequestTcp> requestList;
    private HashMap<String, CommandDebug> commandList;
    private boolean isRunning;
    
    public Console(Client client) {
        this.client = client;
        this.sc = new Scanner(System.in);
        this.RequestListIndexes = new LinkedList<String>();
        this.requestList = new HashMap<String, RequestTcp>();
        commandList = new HashMap<String, CommandDebug>();
        RequestListIndexes.add("ROUTE");
        requestList.put("ROUTE", new RequestTcpRoute());
        commandList.put("kill", new CommandKill());
        isRunning = true;

        System.out.println("############################################");
        System.out.println("##                                        ##");
        System.out.println("##            Console de debug            ##");
        System.out.println("##                                        ##");
        System.out.println("##----------------------------------------##");
        System.out.println("##                                        ##");
        System.out.println("## kill : ferme le programme              ##");
        System.out.println("## ROUTE <addr1> <addr2> : demande trajet ##");
        System.out.println("##                                        ##");
        System.out.println("############################################");
    }

    /**
     * @param index : index de la requête
     * @return : true si la commande existe, false sinon
     */
    private boolean requestExists(String index) {
        return RequestListIndexes.contains(index);
    }

    private boolean commandExists(String index) {
        return commandList.containsKey(index);
    }

    /**
     * @param command : command provenant de l'entrée standard
     * @return : la commande segmentées
     */
    private String[] segmentsCommand(String command) {
        String[] args = command.split(" ");
		return args;
	}

    /**
     * @param command : commande provenant de l'entrée standard
     */
    private void handleCommand(String command) {
        String[] segmentedCommand = segmentsCommand(command);
        if(requestExists(segmentedCommand[0])) {
            if (client != null) {
                String buildedRequest = requestList.get(segmentedCommand[0]).commandBuilder(segmentedCommand);
                if (buildedRequest.equals("undefined")) {
                    System.out.println("Requête non définie dans le protocole");
                } else {
                    client.setExpectedDataIndex(segmentedCommand[0]);
                    client.sendRequest(buildedRequest);
                }
            } else {
                System.out.println("Aucune connexion au serveur");
            }
        } 
        else if (commandExists(segmentedCommand[0])) {
            commandList.get(segmentedCommand[0]).execute(segmentedCommand);
        } else {
            System.out.println("Commande non définie dans le protocole");
        }
    }

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

}
