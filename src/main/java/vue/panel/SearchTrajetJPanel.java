package vue.panel;

import app.server.data.SuggestionStations;
import controller.Controller;
import vue.composant.FlatComboBox;
import vue.composant.FlatJScrollPane;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatComboBox stationDepartList, stationArriveList;
    private final JButton valideJbutton;
    private final JScrollPane paneScroll;
    private final JPanel resultPanel, researchPanel;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanelB;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.stationArriveList = arrivalBox;
        this.stationDepartList = startBox;
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        paneScroll = new FlatJScrollPane(resultPanel);

        valideJbutton.setOpaque(true);
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        setJcomboBox(controler, stationArriveList, SuggestionStations.SuggestionKind.ARRIVAL);
        setJcomboBox(controler, stationDepartList, SuggestionStations.SuggestionKind.DEPART);
        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            resultPanel.add(BuilderJComposant.createJLabelStyle("Recherche en attente ...", 18f, Color.black));
            repaint();
            revalidate();
            String depart = Objects.requireNonNull(stationDepartList.getSelectedItem()).toString();
            String arrive = Objects.requireNonNull(stationArriveList.getSelectedItem()).toString();
            controler.sendRequestRoute("ROUTE;" + depart + ";" + arrive);
        });
        paneScroll.setBorder(BorderFactory.createEmptyBorder());
        add(researchPanel);
        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    private void setJcomboBox(Controller controler, FlatComboBox field, SuggestionStations.SuggestionKind depart) {
        field.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String word = ((JTextField) field.getEditor().getEditorComponent()).getText();
                if (word.matches("[a-zA-Z]+")) {
                    controler.sendRequestSearch("SEARCH;" + word + ";" + depart);
                }
            }
        });
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
