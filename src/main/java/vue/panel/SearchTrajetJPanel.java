package vue.panel;

import controller.Controller;
import vue.composant.FlatJScrollPane;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatJTextField stationDepartList, stationArriveList;
    private final JButton valideJbutton;
    private final JScrollPane paneScroll;
    private final JPanel resultPanel, researchPanel;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanel) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanel;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        paneScroll = new FlatJScrollPane(resultPanel);

        //researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            resultPanel.add(BuilderJComposant.createJLabelStyle("Recherche en attente ...", 18f, Color.black));
            repaint();
            revalidate();
            String depart = stationDepartList.getText();
            String arrive = stationArriveList.getText();
            controler.sendRequestRoute("ROUTE;" + depart + ";" + arrive);
        });
        add(researchPanel);
        add(paneScroll);
        setBackground(new Color(184, 223, 168));
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
