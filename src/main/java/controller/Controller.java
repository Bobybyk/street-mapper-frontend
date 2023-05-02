package controller;

import app.server.data.SuggestionStations;
import console.Console;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Controller {

    private final Console console;

    private final String separator = ";";

    public Controller(Console console) {
        this.console = console;
    }

    /**
     * Fonction dans le controller qui permet d'envoyer un requete de route au serveur
     *
     * @param depart       String de la station de depart
     * @param arrive       String de la station d'arrivé
     * @param typeTrajet   String type de trajet -> distance ou entemps
     * @param sectionAPied Boolean section à pied
     * @param date         date du trajet
     */
    public void sendRequestRoute(String depart, String arrive, String typeTrajet, boolean sectionAPied, Date date) {
        LocalTime time = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
        String arguments = "ROUTE;" + depart + ";" + arrive + ";" + formattedTime + ";" + typeTrajet + ";" + (sectionAPied ? "FOOT" : "");
        console.handleCommand(arguments, separator);
    }

    /**
     * Fonction dans le controller qui permet d'envoyer un requete de search au serveur
     *
     * @param word   String du mot d'entrée de l'utilisateur dans la textfield
     * @param depart Savoir quel type d'input l'utilisateur va cliquer
     */
    public void sendRequestSearch(String word, SuggestionStations.SuggestionKind depart) {
        String arguments = "SEARCH;" + word + ";" + depart;
        console.handleCommand(arguments, separator);
    }

    /**
     * Fonction dans le controller qui permet d'envoyer un requete de Horaire au serveur
     *
     * @param station String de la station
     * @param date    date d'horaire
     */
    public void sendRequestHoraire(String station, Date date) {
        LocalTime time = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = time.format(formatter);
        String arguments = "TIME;" + station + ";" + formattedTime;
        console.handleCommand(arguments, separator);
    }

}
