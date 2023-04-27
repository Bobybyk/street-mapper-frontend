package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.composant.FlatJButton;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * MenuSelectionJPanel est un jpanel
 * ou il y'aura les differents action de l'utilisateur
 */

public class MenuSelectionJPanel extends JPanel {

    private final FlatJButton buttonHistory, buttonSearchTrajet;
    private final JPanel centerPanel;
    private final RootJPanel rootJPanel;
    private final Controller controller;
    private final ResearchPanel researchPanel;
    private FlatComboBox startBox;
    private FlatComboBox arrivalBox;
    private MapJPanel map;

    MenuSelectionJPanel(Controller controller, MapJPanel map, RootJPanel rootJPanel, ResearchPanel researchPanel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        this.controller = controller;
        this.map = map;
        this.researchPanel = researchPanel;
        this.setPreferredSize(new Dimension(250, 100));
        this.startBox = startBox;
        this.arrivalBox = arrivalBox;
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new GridLayout(1, 2));
        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton(Props.recherche, Props.iconPathSearch);
        this.buttonHistory = BuilderJComposant.createJButton(Props.history, Props.iconPathHistory);
        this.add(buttonSearchTrajet);
        this.add(buttonHistory);
        actionListerner();
    }

    private void actionListerner() {
        buttonHistory.addActionListener(actionEvent -> rootJPanel.updateRootPanel(new HistoryTrajetJPanel()));
        buttonSearchTrajet.addActionListener(actionEvent -> rootJPanel.updateRootPanel(new SearchTrajetJPanel(controller, map, researchPanel, startBox, arrivalBox)));
    }

}
