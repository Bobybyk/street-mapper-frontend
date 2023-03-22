package app.vue.panel;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class MenuSelectionJPanel extends JPanel {


    private final JButton buttonHistory, buttonSearchTrajet;
    private final JPanel centerPanel;
    private final RootJPanel rootJPanel;

    MenuSelectionJPanel(RootJPanel rootJPanel){
        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton("Rercherche");
        this.buttonHistory = BuilderJComposant.createJButton("Historique");
        this.setPreferredSize(new Dimension(250, 500));
        this.setBackground(new Color(100,166,74));
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
