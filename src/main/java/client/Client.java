package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

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
     * liste des objets Data reçus par le serveur
     */
    private HashMap<String, Serializable> dataObjectList;
    /**
     * index de la donnée attendue en lecture sur l'ObjectInputStream
     */
    private String expectedDataIndex;
    /**
     * true si le client est connecté au serveur, false sinon
     */
    private boolean isConnected;

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

    @Override
    public void run() {
        System.out.println("Début de l'écoute TCP");
        while(isConnected) {
            if (expectedDataIndex != "") {
                try {
                    Serializable d = (Serializable) ois.readObject();
                    expectedDataIndex = "";
                } catch (ClassNotFoundException e) {
                    System.out.println("Déconnecté !");
                    isConnected = false;
                } catch (IOException e) {
                    System.out.println("Déconnecté !");
                    isConnected = false;
                }
            }
        }
        try {
            this.socket.close();
        } catch (IOException e) {
            e.printStackTrace();
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

}
