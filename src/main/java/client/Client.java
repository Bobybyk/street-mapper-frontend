package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import data.Data;

public class Client implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private ObjectInputStream ois;
    private PrintWriter out;
    private HashMap<String, Data> dataObjectList;
    private String expectedDataIndex;
    private boolean isConnected;

    public Client(String ip, int port) {
        try {
            System.out.println("Création de la connection TCP avec le serveur...");
            this.socket = new Socket(ip, port);
            this.out = new PrintWriter(this.socket.getOutputStream());
            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.ois = new ObjectInputStream(this.socket.getInputStream());
            expectedDataIndex = "";
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
        while(isConnected) {
            if (expectedDataIndex != "") {
                try {
                    dataObjectList.put(expectedDataIndex, (Data) ois.readObject());
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

}
