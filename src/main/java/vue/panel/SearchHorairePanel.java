package vue.panel;

import controller.Controller;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;

public class SearchHorairePanel extends JPanel {

    private final FlatJTextField stationSearch;
    private final JList<String> heure, minute;
    private final JButton valideJbutton;
    private final JPanel resultPanel, researchPanel;

    SearchHorairePanel(Controller controler){
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = BuilderJComposant.createPanelBoxLayoutVertical();
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationSearch = BuilderJComposant.createFlatJTextField(Props.station);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        this.heure = BuilderJComposant.createJList((BuilderJComposant.fillJListString("00", "01", "O2", "03")));
        this.minute = BuilderJComposant.createJList((BuilderJComposant.fillJListString("10", "15", "20", "25")));

        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationSearch);
        researchPanel.add(heure);
        researchPanel.add(minute);
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            String station = stationSearch.getText();

        });
        add(researchPanel);
        add(resultPanel);
        setBackground(new Color(184,223,168));
    }

}