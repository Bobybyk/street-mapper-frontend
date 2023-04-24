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

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanelB;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        paneScroll = new FlatJScrollPane(resultPanel);

        valideJbutton.setOpaque(true);
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        //researchPanel.setBorder(/*BorderFactory.createLineBorder(Color.BLACK)*/);
        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            resultPanel.add(BuilderJComposant.createJLabelStyle("Recherche en attente ...", 18f, Color.black));
            repaint();
            revalidate();
            String depart = stationDepartList.getText();
            String arrive = stationArriveList.getText();
            controler.sendRequestRoute("ROUTE;" + depart + ";" + arrive);
        });
        paneScroll.setBorder(BorderFactory.createEmptyBorder());
        add(researchPanel);
        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
