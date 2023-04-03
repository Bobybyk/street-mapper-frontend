package app.vue.panel;

import app.vue.composant.FlatJScrollPane;
import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

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
        setPreferredSize(new Dimension(650, 500));

        panelTrajetHistorique = BuilderJComposant.createPanelBoxLayoutVertical();
        paneScroll = new FlatJScrollPane(panelTrajetHistorique);
        for (int i = 0; i < 5; i++) panelTrajetHistorique.add(new ListTrajetPanel());
        final JLabel jlabel = new JLabel("La liste des vos derniers trajets recherchées");
        jlabel.setFont(BuilderJComposant.lemontRegularFont(22));
        add(jlabel);
        add(paneScroll);
    }



}
