package controller;

import app.server.data.SuggestionStations;
import client.Client;
import commands.tcp.RequestIndexesList;

import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Controller {

    private final Client client;

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
     * @param date date du trajet
     */
    public void sendRequestRoute(String depart, String arrive, String typeTrajet, boolean sectionAPied, Date date) {
        LocalTime time = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
        String arguments = "ROUTE;"+depart+";"+arrive+";"+ formattedTime+";"+ typeTrajet+";"+ (sectionAPied ? "FOOT\n" :"\n");
        System.out.println(arguments);
        client.setNextRequest(arguments, RequestIndexesList.ROUTE);
    }

    public void sendRequestSearch(String word, SuggestionStations.SuggestionKind depart) {
        String requete = "SEARCH;" + word + ";" + depart;
        client.setNextRequest(requete, RequestIndexesList.SEARCH);
    }
}
