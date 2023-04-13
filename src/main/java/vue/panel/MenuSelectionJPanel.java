package vue.panel;

import controller.Controller;
import vue.composant.FlatJButton;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;

/**
 * MenuSelectionJPanel est un jpanel
 * ou il y'aura les differents action de l'utilisateur
 *
 */

public class MenuSelectionJPanel extends JPanel {

    private final FlatJButton buttonHistory, buttonSearchTrajet;
    private final JPanel centerPanel;
    private final RootJPanel rootJPanel;
    private final Controller controller;
    private final ResearchPanel researchPanel;


    MenuSelectionJPanel(Controller controller, RootJPanel rootJPanel, ResearchPanel researchPanel){
        this.controller = controller;
        this.researchPanel = researchPanel;
        this.setPreferredSize(new Dimension(250, 125));
        this.setBackground(new Color(241, 242, 246));
        this.setLayout(new GridLayout(1, 2));
        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton(Props.recherche, Props.iconPathSearch);
        this.buttonHistory = BuilderJComposant.createJButton(Props.history,Props.iconPathHistory);
        this.add(buttonSearchTrajet);
        this.add(buttonHistory);
        actionListerner();
    }

    private void actionListerner() {
        buttonHistory.addActionListener(actionEvent -> rootJPanel.updateRootPanel(new HistoryTrajetJPanel()));
        buttonSearchTrajet.addActionListener(actionEvent -> rootJPanel.updateRootPanel(new SearchTrajetJPanel(controller, researchPanel)));
    }

}
