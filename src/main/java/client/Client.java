package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import commands.tcp.CommandTcpAsk;
import commands.tcp.CommandTcpRcv;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private HashMap<String, CommandTcpAsk> commandListAsk;
    private HashMap<String, CommandTcpRcv> commandListRcv;
    private boolean isConnected;

    public Client(String ip, int port) {
        try {
            System.out.println("Création de la connection TCP avec le serveur...");
            this.socket = new Socket(ip, port);
            this.out = new PrintWriter(this.socket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.isConnected = true;
            System.out.println("...succès");
        } catch (UnknownHostException e) {
            System.out.println("[Client/ERREUR] : L'adresse IP de l'hôte ne peut être déterminée");
        } catch (IOException e) {
            System.out.println("[Client/ERREUR] : Numero de PORT INDISPONIBLE ou IP INCONNUE");
        }
    }

    @Override
    public void run() {
        System.out.println("Début de l'écoute TCP");
        String request;
        while(isConnected) {
            try {
                request = in.readLine();
                if(request == null) {
                	System.out.println("Déconnecté !");
                	isConnected = false;
                } else {
                    handleRequest(request);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param request : la requête reçu du serveur
     */
    private void handleRequest(String request) {
        String[] segmentedMsg = segmentsMessage(request);
        if (commandExists(segmentedMsg[0])) {
            // TODO : déterminer l'argument adéquat à passer à la fonction commandExec
            commandListRcv.get(segmentedMsg[0]).commandExec(request);
        } else {
            System.out.println("[Client/ERREUR] : Commande inconnue");
        }
    }

    /**
     * @param data : données reçu du serveur à parser
     * @return : les données segmentées
     */
    private String[] segmentsMessage(String data) {
        String[] args = data.split(" ");
		return args;
	}

    /**
     * @param command : commande reçu du serveur
     * @return : true si la commande existe, false sinon
     */
    private boolean commandExists(String command) {
        return commandListAsk.containsKey(command);
    }

}
