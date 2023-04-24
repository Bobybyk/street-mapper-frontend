package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.composant.FlatJScrollPane;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
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

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanelB;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.stationArriveList = new FlatComboBox(new String[]{});//BuilderJComposant.createFlatJTextField(Props.arrive);
        this.stationDepartList = new FlatComboBox(new String[]{});//BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        paneScroll = new FlatJScrollPane(resultPanel);

        valideJbutton.setOpaque(true);
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        //researchPanel.setBorder(/*BorderFactory.createLineBorder(Color.BLACK)*/);
        setJcomboBox(controler, stationArriveList);
        setJcomboBox(controler, stationDepartList);
        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            resultPanel.add(BuilderJComposant.createJLabelStyle("Recherche en attente ...", 18f, Color.black));
            repaint();
            revalidate();
            String depart = Objects.requireNonNull(stationDepartList.getSelectedItem()).toString();//.getText();
            String arrive = Objects.requireNonNull(stationArriveList.getSelectedItem()).toString();//.getText();
            controler.sendRequestRoute("ROUTE;" + depart + ";" + arrive);
        });
        paneScroll.setBorder(BorderFactory.createEmptyBorder());
        add(researchPanel);
        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    private void setJcomboBox(Controller controler, FlatComboBox field){
        ((JTextField) field.getEditor().getEditorComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controler.sendRequestSearch("SEARCH;" + ((JTextField) field.getEditor().getEditorComponent()).getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controler.sendRequestSearch("SEARCH;" + ((JTextField) field.getEditor().getEditorComponent()).getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
