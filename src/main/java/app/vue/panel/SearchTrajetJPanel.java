package app.vue.panel;

import app.vue.composant.FlatJTextField;
import app.vue.utils.BuilderJComposant;
import app.vue.utils.Props;

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
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = BuilderJComposant.createPanelBoxLayoutVertical();
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            /*resultPanel.add(new ListTrajetPanel(new Trajet()));*/
            revalidate();
            repaint();
        });
        add(researchPanel);
        add(resultPanel);
        setBackground(new Color(184,223,168));
    }

}
