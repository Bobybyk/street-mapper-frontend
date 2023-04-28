package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import app.map.StationInfo;
import app.server.data.ErrorServer;
import app.server.data.Route;
import app.server.data.SuggestionStations;
import commands.tcp.RequestIndexesList;
import console.Debug;
import console.DebugList;
import data.DataList;
import utils.Observer;
import vue.composant.FlatComboBox;
import vue.panel.ListTrajetPanel;
import vue.panel.MapJPanel;
import vue.panel.ResearchPanel;
import vue.panel.RootJPanel;

import javax.swing.*;

public class Client implements Runnable, Observer {

    private final ResearchPanel researchPanel;

    private final FlatComboBox startBox;

    private final FlatComboBox arrivalBox;
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

    @Override
    public void update(Object researchPanel) {
        if(DataList.route instanceof Route route){
            JPanel resultPanel = (JPanel) researchPanel;
            resultPanel.removeAll();
            resultPanel.add(new ListTrajetPanel(route));
            resultPanel.repaint();
            resultPanel.revalidate();
        }else if(DataList.station instanceof SuggestionStations sugg){
            String[] arr = sugg.getStations().stream().map(StationInfo::getStationName).toArray(String[]::new);
            if (sugg.getKind()==SuggestionStations.SuggestionKind.ARRIVAL){
                SwingUtilities.invokeLater(() -> {
                    arrivalBox.removeAllItems();
                    for (String s : arr) {
                        arrivalBox.addItem(s);
                    }
                });
            }else{
                SwingUtilities.invokeLater(() -> {
                    startBox.removeAllItems();
                    for (String s : arr) {
                        startBox.addItem(s);
                    }
                });
            }
        }else if(DataList.route instanceof ErrorServer error){
            JPanel resultPanel = (JPanel) researchPanel;
            resultPanel.removeAll();
            resultPanel.add(new JLabel("Erreur: " + error.getError().toLowerCase()));
            resultPanel.repaint();
            resultPanel.revalidate();
        }else{
            JPanel resultPanel = (JPanel) researchPanel;
            resultPanel.removeAll();
            resultPanel.add(new JLabel("Erreur"));
            Debug.print(DebugList.WARNING, "[WARNING/Client] données reçues du serveur non reconnues");
            resultPanel.repaint();
            resultPanel.revalidate();
        }
    }

    public Client(String ip, int port, ResearchPanel researchPanel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        this.researchPanel = researchPanel;
        this.startBox = startBox;
        this.arrivalBox = arrivalBox;
        try {
            Debug.print(DebugList.NETWORK, "Création de la connection TCP avec le serveur...");
            socket = new Socket(ip, port);
            out = new PrintWriter(socket.getOutputStream());
            isConnected = true;
            Debug.print(DebugList.NETWORK, "...succès");
        } catch (UnknownHostException e) {
            Debug.print(DebugList.NETWORK, "L'adresse IP de l'hôte ne peut être déterminée");
            Debug.print(DebugList.NETWORK, "...la connexion au serveur a échoué");
        } catch (IOException e) {
            Debug.print(DebugList.NETWORK, "Numero de PORT INDISPONIBLE ou IP INCONNUE");
            Debug.print(DebugList.NETWORK, "...la connexion au serveur a échoué");
        }
    }

    /**
     * lit les données envoyées par le serveur et gère les erreurs
     */
    private Serializable readServerData() {
        try {
            Debug.print(DebugList.NETWORK, "En attente de données du serveur");
            ois = new ObjectInputStream(socket.getInputStream());
            return (Serializable) ois.readObject();
        } catch (ClassNotFoundException e) {
            Debug.print(DebugList.ERROR, "[ERREUR/Client] Erreur lors de la récupération des données");
            kill();
        } catch (IOException e) {
            Debug.print(DebugList.ERROR, "[ERREUR/Client] Erreur lors de la récupération des données");
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
            case RequestIndexesList.ROUTE -> {
                DataList.route = serverData;
                researchPanel.notifyObservers();
            }
            case RequestIndexesList.SEARCH -> {
                DataList.station = serverData;
                if(DataList.station instanceof SuggestionStations sugg){
                    if (sugg.getKind()== SuggestionStations.SuggestionKind.ARRIVAL){
                        arrivalBox.notifyObservers();
                    }else{
                        startBox.notifyObservers();
                    }
                }
            }
            case RequestIndexesList.TIME -> DataList.timeStation = serverData;
            default -> Debug.print(DebugList.WARNING, "[WARNING/Client] Les données attendues sont inconnues et seront ignorées");
        }
    }

    @Override
    public void run() {
        Debug.print(DebugList.NETWORK, "Début de l'écoute TCP");
        while (isConnected) {
            sendRequest();
            Serializable serverData = readServerData();
            Debug.print(DebugList.INFO, "Nouvelles données reçues du serveur !");
            if (serverData == null) {
                Debug.print(DebugList.ERROR, "[ERREUR/Client] erreur lors de la récupération des données");
                kill();
            }
            handleReceivedData(serverData);
        }
    }

    /**
     * envoie au serveur la dernière requête enregistrée si aucunes données sont en
     * transfert
     */
    public synchronized void sendRequest() {
        while (nextRequestToSend == null || nextExpectedDataIndex == null) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        expectedDataIndex = nextExpectedDataIndex;
        out.println(nextRequestToSend);
        nextRequestToSend = null;
        nextExpectedDataIndex = null;
        out.flush();
        Debug.print(DebugList.INFO, "Requête envoyée au serveur : " + expectedDataIndex);
    }

    /**
     * @param request   prochaine requête à envoyer au serveur l'ObjectInputStream
     * @param dataIndex index de la donnée attendue en lecture sur
     *                  l'ObjectInputStream
     */
    public synchronized void setNextRequest(String request, String dataIndex) {
        this.nextRequestToSend = request;
        this.nextExpectedDataIndex = dataIndex;
        this.notify();
        Debug.print(DebugList.INFO, "Nouvelle requête enregistrée : " + request);
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
            Debug.print(DebugList.NETWORK, "Client déconnecté !");
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
