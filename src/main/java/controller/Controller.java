package controller;

import client.Client;
import commands.tcp.RequestIndexesList;

import javax.swing.*;
import java.util.Date;

public class Controller {

    private Client client;

    public Controller(Client client){
        this.client = client;
    }

    /**
     *
     * Fonction dans le controller qui permet d'envoyer un requete au serveur
     *
     * @param depart String de la station de depart
     * @param arrive String de la station d'arrivé
     * @param typeTrajet String type de trajet -> distance ou entemps
     * @param sectionAPied Boolean section à pied
     * @param heureDate date du trajet
     */
    public void sendRequestRoute(String depart, String arrive, String typeTrajet, boolean sectionAPied, Date heureDate) {
        String arguments = "ROUTE;"+depart+";"+arrive; /** TODO: reste du protocole à finir matthieu-maryline */

        client.setNextRequest(arguments, RequestIndexesList.ROUTE);
    }
}
