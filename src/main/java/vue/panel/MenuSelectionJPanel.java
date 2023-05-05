package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.composant.FlatJButton;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;

/**
 * MenuSelectionJPanel est un jpanel
 * ou il y'aura les differents action de l'utilisateur
 */

public class MenuSelectionJPanel extends JPanel {

    private final FlatJButton buttonHistory;
    private final FlatJButton buttonSearchTrajet;
    private final FlatJButton buttonSearchHoraire;
    private final RootJPanel rootJPanel;
    private final transient Controller controller;
    private final ResearchPanel researchPanel;
    private final FlatComboBox startBox;
    private final FlatComboBox arrivalBox;

    MenuSelectionJPanel(Controller controller, RootJPanel rootJPanel, ResearchPanel researchPanel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        this.controller = controller;
        this.researchPanel = researchPanel;
        this.setPreferredSize(new Dimension(250, 100));
        this.startBox = startBox;
        this.arrivalBox = arrivalBox;
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(new GridLayout(1, 3));
        this.rootJPanel = rootJPanel;
        this.buttonSearchTrajet = BuilderJComposant.createJButton(Props.RECHERCHE, Props.ICON_PATH_SEARCH);
        this.buttonHistory = BuilderJComposant.createJButton(Props.HISTORY, Props.ICON_PATH_HISTORY);
        this.buttonSearchHoraire = BuilderJComposant.createJButton(Props.HORAIRE, Props.ICON_PATH_TIME);

        this.add(buttonSearchTrajet);
        this.add(buttonSearchHoraire);
        this.add(buttonHistory);
        actionListerner();
    }

    private void actionListerner() {
        buttonHistory.addActionListener(actionEvent -> rootJPanel.updateRootPanel(new HistoryTrajetJPanel()));
        buttonSearchTrajet.addActionListener(actionEvent -> {
            resetInput();
            rootJPanel.updateRootPanel(new SearchTrajetJPanel(controller, researchPanel, startBox, arrivalBox));
        });
        buttonSearchHoraire.addActionListener(actionEvent -> {
            resetInput();
            rootJPanel.updateRootPanel(new SearchHorairePanel(controller, researchPanel, startBox));
        });
    }

    private void resetInput(){
        researchPanel.removeAll();
        startBox.reset(Props.DEPART);
        arrivalBox.reset(Props.ARRIVE);
        startBox.repaint();
        startBox.revalidate();
        arrivalBox.repaint();
        arrivalBox.revalidate();
    }
}
