package app.vue;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class MenuSelectionJPanel extends JPanel {


    private JButton buttonHistory, buttonSearchTrajet;
    private JPanel centerPanel;
    private RootJPanel rootJPanel;

    MenuSelectionJPanel(RootJPanel rootJPanel){
        this.rootJPanel = rootJPanel;
        this.centerPanel = new JPanel();
        this.buttonSearchTrajet = BuilderJComposant.createJButton("Rercherche");
        this.buttonHistory = BuilderJComposant.createJButton("Mes trajets");
        this.setLayout(new GridLayout(2, 1));
        this.setPreferredSize(new Dimension(250, 500));
        this.setBackground(new Color(245,255,250));
        this.add(buttonSearchTrajet);
        this.add(buttonHistory);
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
