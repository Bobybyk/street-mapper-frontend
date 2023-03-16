package console;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import client.Client;
import requests.tcp.RequestTcp;

public class Console implements Runnable {
    private Client client;
    private Scanner sc;
    private LinkedList<String> requestList;
    private HashMap<String, RequestTcp> commandListAsk;
    
    public Console(Client client) {
        this.client = client;
        this.sc = new Scanner(System.in);
        this.requestList = new LinkedList<String>();
        requestList.add("ROUTE");
    }

    /**
     * @param index : index de la requête
     * @return : true si la commande existe, false sinon
     */
    private boolean requestExists(String index) {
        return requestList.contains(index);
    }

    /**
     * @param request : requête provenant de l'entrée standard
     * @return : les données segmentées
     */
    private String[] segmentsRequest(String request) {
        String[] args = request.split(" ");
		return args;
	}

    /**
     * @param request : requête provenant de l'entrée standard
     */
    private void handleRequest(String request) {
        String[] segmentedRequest = segmentsRequest(request);
        if(!requestExists(segmentedRequest[0])) {
            System.out.println("Requête non définie dans le protocole");
        } else {
            String buildedRequest = commandListAsk.get(segmentedRequest[0]).commandBuilder(segmentedRequest);
            if (buildedRequest.equals("undefined")) {
                System.out.println("Requête non définie dans le protocole");
            } else {
                client.setExpectedDataIndex(segmentedRequest[0]);
                client.sendRequest(buildedRequest);
            }
        }
    }
    
    @Override
    public void run() {
        handleRequest(sc.nextLine());
    }

}
