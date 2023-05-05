package vue.panel;

import utils.RouteSerializer;
import vue.composant.FlatJScrollPanel;
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

    HistoryTrajetJPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(184, 223, 168));
        setPreferredSize(new Dimension(650, 700));
        JPanel panelTrajetHistorique = BuilderJComposant.createPanelBoxLayoutVertical();
        LinkedList<Route> route = new LinkedList<>(RouteSerializer.getListRoute());
        for (Route value : route) {
            panelTrajetHistorique.add(new ListTrajetPanel(value));
        }
        JScrollPane paneScroll = new FlatJScrollPanel(panelTrajetHistorique);
        final JLabel jlabel = new JLabel(Props.LIST_TRAJETS);
        jlabel.setFont(BuilderJComposant.lemontRegularFont(22));
        add(jlabel);
        add(paneScroll);
    }



}
