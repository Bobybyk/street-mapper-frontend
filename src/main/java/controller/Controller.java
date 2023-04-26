package controller;

import client.Client;
import commands.tcp.RequestIndexesList;

import javax.swing.*;

public class Controller {

    private Client client;

    public Controller(Client client){
        this.client = client;
    }


    public void sendRequestRoute(String text, String text1, String typeTrajet, boolean selected, SpinnerDateModel value) {
    }
}
