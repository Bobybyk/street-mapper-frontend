package controller;

import client.Client;
import commands.tcp.RequestIndexesList;

public class Controller {

    private Client client;

    public Controller(Client client){
        this.client = client;
    }

    /**
     *
     * @param arguments
     */
    public void sendRequestRoute(String arguments){
        client.setNextRequest(arguments, RequestIndexesList.ROUTE);
    }

    public void sendRequestSearch(String arguments){
        client.setNextRequest(arguments, RequestIndexesList.SEARCH);
    }

}
