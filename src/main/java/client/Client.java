package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import app.server.data.ErrorServer;
import app.server.data.Route;
import commands.tcp.RequestIndexesList;
import data.DataList;

public class Client implements Runnable {
    /**
     * socket pour assurer la communication TCP avec le serveur
     */
    private Socket socket;
    /**
     * pour lecture des objets émis par le serveur
     */
    private ObjectInputStream ois;
    /**
     * pour écriture des requêtes TCP au serveur
     */
    private PrintWriter out;
    /**
     * index de la donnée attendue en lecture sur l'ObjectInputStream
     */
    private String expectedDataIndex;
    /**
     * index de la donnée pour la prochaine requête
     */
    private String nextExpectedDataIndex;
    /**
     * prochaine requête à envoyer au serveur
     */
    private String nextRequestToSend;
    /**
     * true si le client est connecté au serveur, false sinon
     */
    private boolean isConnected;

    public Client(String ip, int port) {
        try {
            System.out.println("Création de la connection TCP avec le serveur...");
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream());
            isConnected = true;
            System.out.println("...succès");
        } catch (UnknownHostException e) {
            System.out.println("[ERREUR] : L'adresse IP de l'hôte ne peut être déterminée");
            System.out.println("La connexion au serveur a échoué");
        } catch (IOException e) {
            System.out.println("[ERREUR] : Numero de PORT INDISPONIBLE ou IP INCONNUE");
            System.out.println("...la connexion au serveur a échoué");
        }
    }

    /**
     * lit les données envoyées par le serveur et gère les erreurs
     */
    private Serializable readServerData() {
        try {
            return (Serializable) ois.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des données");
            kill();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de la récupération des données");
            kill();
        }
        return null;
    }

    /**
     * gère les données reçues du serveur
     *
     * @param serverData données envoyées par le serveur
     */
    private void handleReceivedData(Serializable serverData) {
        switch (expectedDataIndex) {
            case RequestIndexesList.ROUTE:
                DataList.route = serverData;
                break;
            default:
                System.out.println("Les données envoyées par le serveur sont inconnues et seront ignorées");
                break;
        }
    }

    @Override
    public void run() {
        System.out.println("Début de l'écoute TCP");
        try {
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (isConnected) {
            sendRequest();
            Serializable serverData = readServerData();
            if (serverData == null) {
                System.out.println("Erreur lors de la récupération des données");
                kill();
            }
            handleReceivedData(serverData);
        }
    }

    /**
     * envoie au serveur la dernière requête enregistrée si aucunes données sont en
     * transfert
     */
    public void sendRequest() {
        while (nextRequestToSend == null || nextExpectedDataIndex == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        expectedDataIndex = nextExpectedDataIndex;
        out.println(nextRequestToSend);
        nextRequestToSend = null;
        nextExpectedDataIndex = null;
        out.flush();
    }

    /**
     * @param request           prochaine requête à envoyer au serveur
     *                          l'ObjectInputStream
     */
    public void setNextRequest(String request, String dataIndex) {
        this.nextRequestToSend = request;
        this.nextExpectedDataIndex = dataIndex;
        notify();
    }

    /**
     * @return true si le client est connecté au serveur, false sinon
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * @return : true si le client a réussi à se déconnecter du serveur, false sinon
     */
    public boolean kill() {
        try {
            this.socket.close();
            this.isConnected = false;
            System.out.println("Client déconnecté !");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }
}