package app.vue.panel;

import app.vue.utils.BuilderJComposant;
import map.Trajet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HistoryTrajetJPanel extends JPanel {

    private JPanel panelTrajetHistorique;
    private JScrollPane paneScroll;

    HistoryTrajetJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        panelTrajetHistorique = new JPanel();
        paneScroll = new JScrollPane(panelTrajetHistorique);
        paneScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        paneScroll.setViewportBorder(BorderFactory.createEmptyBorder());
        panelTrajetHistorique.setLayout(new BoxLayout(panelTrajetHistorique, BoxLayout.Y_AXIS));
        setBackground(new Color(184, 223, 168));
        setPreferredSize(new Dimension(650, 500));
        for (int i = 0; i < 5; i++) panelTrajetHistorique.add(new ListTrajetPanel(new Trajet()));
        add(new JLabel("<html> <h3>La liste des vos derniers trajets recherchées : </h3></html>"));
        add(paneScroll);
    }



}
