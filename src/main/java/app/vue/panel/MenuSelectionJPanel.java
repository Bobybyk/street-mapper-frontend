package app.vue.panel;

import app.vue.composant.FlatJButton;
import app.vue.utils.BuilderJComposant;

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

    MenuSelectionJPanel(RootJPanel rootJPanel){
        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton("Rercherche", "src/main/resources/icon/recherche_icon.png");
        this.buttonHistory = BuilderJComposant.createJButton("Historique","src/main/resources/icon/history_icon.png");
        this.setPreferredSize(new Dimension(250, 125));
        this.setBackground(new Color(241, 242, 246));
        this.add(buttonSearchTrajet, CENTER_ALIGNMENT);
        this.add(buttonHistory, CENTER_ALIGNMENT);
        actionListerner();
    }

    private void actionListerner() {
        buttonHistory.addActionListener(actionEvent -> {
            rootJPanel.updateRootPanel(new HistoryTrajetJPanel());
        });

        buttonSearchTrajet.addActionListener(actionEvent -> {
            rootJPanel.updateRootPanel(new SearchTrajetJPanel());
        });
    }

}
