package app.vue.panel;

import app.vue.composant.FlatJScrollPane;
import map.Trajet;

import javax.swing.*;
import java.awt.*;

public class HistoryTrajetJPanel extends JPanel {


    /**
     *
     * HistoryTrajetJPanel est un Jpanel pour avoir
     * l'historique
     *
     */
    private JPanel panelTrajetHistorique;
    private JScrollPane paneScroll;

    HistoryTrajetJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panelTrajetHistorique = new JPanel();
        paneScroll = new FlatJScrollPane(panelTrajetHistorique);

        panelTrajetHistorique.setLayout(new BoxLayout(panelTrajetHistorique, BoxLayout.Y_AXIS));
        setBackground(new Color(184, 223, 168));
        setPreferredSize(new Dimension(650, 500));
        for (int i = 0; i < 5; i++) panelTrajetHistorique.add(new ListTrajetPanel(new Trajet()));
        add(new JLabel("<html> <h3>La liste des vos derniers trajets recherchées : </h3></html>"));
        add(paneScroll);
    }



}
