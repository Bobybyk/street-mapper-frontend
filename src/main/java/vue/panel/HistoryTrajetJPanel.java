package vue.panel;

import vue.composant.FlatJScrollPane;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;

import server.data.Route;

import java.awt.*;
import java.util.LinkedList;

/**
 *
 * HistoryTrajetJPanel est un Jpanel pour avoir
 * l'historique
 *
 */

public class HistoryTrajetJPanel extends JPanel {

    private JPanel panelTrajetHistorique;
    private JScrollPane paneScroll;

    HistoryTrajetJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(184, 223, 168));
        setPreferredSize(new Dimension(650, 700));
        panelTrajetHistorique = BuilderJComposant.createPanelBoxLayoutVertical();
        LinkedList<Route> route = RouteSerializer.getListRoute();
        for (Route value : route) {
            panelTrajetHistorique.add(new ListTrajetPanel(value));
        }
        paneScroll = new FlatJScrollPane(panelTrajetHistorique);
        final JLabel jlabel = new JLabel(Props.listTrajets);
        jlabel.setFont(BuilderJComposant.lemontRegularFont(22));
        add(jlabel);
        add(paneScroll);
    }



}
