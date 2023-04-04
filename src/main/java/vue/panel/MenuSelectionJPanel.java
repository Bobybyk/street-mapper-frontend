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


    MenuSelectionJPanel(Controller controller, RootJPanel rootJPanel){
        this.controller = controller;
        this.setPreferredSize(new Dimension(250, 125));
        this.setBackground(new Color(241, 242, 246));

        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton(Props.recherche, Props.iconPathSearch);
        this.buttonHistory = BuilderJComposant.createJButton(Props.history,Props.iconPathHistory);
        this.add(buttonSearchTrajet, CENTER_ALIGNMENT);
        this.add(buttonHistory, CENTER_ALIGNMENT);
        actionListerner();
    }

    private void actionListerner() {
        buttonHistory.addActionListener(actionEvent -> {
            rootJPanel.updateRootPanel(new HistoryTrajetJPanel());
        });

        buttonSearchTrajet.addActionListener(actionEvent -> {
            rootJPanel.updateRootPanel(new SearchTrajetJPanel(controller));
        });
    }

}