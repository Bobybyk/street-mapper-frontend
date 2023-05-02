/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package app;

import client.Client;
import console.Console;
import console.Debug;
import console.DebugList;
import controller.Controller;
import vue.MainWindowJFrame;
import vue.composant.FlatComboBox;
import vue.panel.ResearchPanel;
import vue.utils.Props;

import javax.swing.*;

public class App {
    /**
     * The host to connect to.
     */
    private static final String HOST = "localhost";
    /**
     * The port to connect to.
     */
    private static final int PORT = 12345;

    public static void main(String[] args) {
        ResearchPanel researchPanel = new ResearchPanel();
        researchPanel.setOpaque(false);
        researchPanel.setLayout(new BoxLayout(researchPanel, BoxLayout.Y_AXIS));
        FlatComboBox stationDepartList = new FlatComboBox(Props.depart);
        FlatComboBox stationArriveList = new FlatComboBox(Props.arrive);
        Client client = new Client(HOST, PORT, researchPanel, stationDepartList, stationArriveList);
        Console console = new Console(client);
        Controller controller = new Controller(console);
        researchPanel.addObserver(client);
        stationDepartList.addObserver(client);
        stationArriveList.addObserver(client);
        if (client.isConnected()) {
            client.start();
            SwingUtilities.invokeLater(() -> new MainWindowJFrame(controller, researchPanel, stationDepartList, stationArriveList));
            console.start();
        } else {
            Debug.print(DebugList.NETWORK, Props.clientInvalide);
            client.kill();
        }
    }
}
