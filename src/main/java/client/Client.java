package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import data.DataList;
import data.Route;

public class Client extends Thread {
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
     * true si le client est connecté au serveur, false sinon
     */
    private boolean isConnected;
    /**
     * nombre de requêtes envoyées au serveur
     */
    private int sendedRequestCount;

    public Client(String ip, int port) {
        try {
            System.out.println("Création de la connection TCP avec le serveur...");
            this.socket = new Socket(ip, port);
            this.out = new PrintWriter(this.socket.getOutputStream());
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            expectedDataIndex = "";
            this.isConnected = true;
            System.out.println("...succès");
        } catch (UnknownHostException e) {
            System.out.println("[ERREUR] : L'adresse IP de l'hôte ne peut être déterminée");
            System.out.println("La connexion au serveur a échoué");
        } catch (IOException e) {
            System.out.println("[ERREUR] : Numero de PORT INDISPONIBLE ou IP INCONNUE");
            System.out.println("...la connexion au serveur a échoué");
        }
    }

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

    private void handleReceivedData(Serializable serverData) {
        switch (expectedDataIndex) {
            case "route":
                DataList.route = (Route) serverData;
                break;
            default:
                System.out.println("Les données envoyées par le serveur sont inconnues et seront ignorées");
                break;
        }
    }

    @Override
    public void run() {
        System.out.println("Début de l'écoute TCP");
        while(isConnected) {
            Serializable serverData = readServerData();
            if (serverData == null) {
                System.out.println("Erreur lors de la récupération des données");
                kill();
            }
            else if (sendedRequestCount == 1) {
                handleReceivedData(serverData);
                sendedRequestCount--;
            } else {
                sendedRequestCount--;
            }
            expectedDataIndex = "";
        }
    }

    /**
     * @param request : requête à envoyer au serveur
     */
    public void sendRequest(String request) {
        this.out.println(request);
        this.out.flush();
    }

    /**
     * @param expectedDataIndex : index de la donnée attendue en lecture sur l'ObjectInputStream
     */
    public void setExpectedDataIndex(String expectedDataIndex) {
        this.expectedDataIndex = expectedDataIndex;
    }

    /**
     * @return : true si le client est connecté au serveur, false sinon
     */
    public boolean isConnected() {
        return isConnected;
    }

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

}
