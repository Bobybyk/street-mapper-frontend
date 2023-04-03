package app.vue.panel;

import app.vue.composant.FlatJTextField;
import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatJTextField stationDepartList, stationArriveList;
    private final JButton valideJbutton;

    private JPanel resultPanel, researchPanel;

    SearchTrajetJPanel(){
        this.resultPanel = new JPanel();
        this.researchPanel = new JPanel();
        this.stationArriveList = new FlatJTextField("Depart");
        this.stationDepartList = new FlatJTextField("Arrive");
        this.valideJbutton = BuilderJComposant.createJButton("Valider");

        setPreferredSize(new Dimension(650, 500));
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        researchPanel.setLayout(new BoxLayout(researchPanel, BoxLayout.X_AXIS));
        valideJbutton.addActionListener(e ->{
            /*resultPanel.add(new ListTrajetPanel(new Trajet()));*/
            revalidate();
            repaint();
        });
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));
        add(researchPanel);
        add(resultPanel);
        setBackground(new Color(184,223,168));
    }

}
