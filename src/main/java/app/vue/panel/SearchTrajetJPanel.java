package app.vue.panel;

import app.vue.composant.FlatSearchBar;
import app.vue.utils.BuilderJComposant;
import map.Trajet;

import javax.swing.*;
import java.awt.*;

public class SearchTrajetJPanel extends JPanel {

    /**
     * SearchTrajetJPanel est un jpanel
     * c'est ici qu'on affiche le resultat de recherche
     */
    private final JList<String> stationDepartList;
    private final JList<String> stationArriveList;
    private final JButton valideJbutton;

    private JPanel resultPanel, researchPanel;

    SearchTrajetJPanel(){
        this.resultPanel = new JPanel();
        this.researchPanel = new JPanel();
        String test[] = {"Chatelet", "Avenue de France", "barbes", "François Miterrand"};
        this.stationArriveList = new JList<>(test);
        this.stationDepartList = new JList<>(test);
        this.valideJbutton = BuilderJComposant.createJButton("Valider");
        setPreferredSize(new Dimension(650, 500));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);

        valideJbutton.addActionListener(e ->{
            resultPanel.add(new ListTrajetPanel(new Trajet()));
            resultPanel.add(new ListTrajetPanel(new Trajet()));
            resultPanel.add(new ListTrajetPanel(new Trajet()));
            revalidate();
            repaint();
        });
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        add(researchPanel);
        add(resultPanel);
        // create a splitpane
        setBackground(new Color(184,223,168));
    }

}
