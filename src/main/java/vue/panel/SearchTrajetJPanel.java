package vue.panel;

import controller.Controller;
import vue.composant.FlatJRadioButton;
import vue.composant.FlatJScrollPane;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatJTextField stationDepartList, stationArriveList;
    private final JButton valideJbutton;
    private final JScrollPane paneScroll;
    private final JPanel resultPanel, researchPanel, optionPanel;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanelB;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.optionPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.arrive);

        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);


        paneScroll = new FlatJScrollPane(resultPanel);

        optionPanelLoad();

        optionPanel.setName("Option de recherche");
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

        SpinnerDateModel model = new SpinnerDateModel();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(calendar.getTime());
        //calendar.set
      //  calendar.set(2023, Calendar.APRIL, 25);
        model.setValue(calendar.getTime());

        // Créez une instance de la classe JSpinner
        JSpinner spinner = new JSpinner(model);

        // Ajoutez le composant JSpinner à la fenêtre
        add(researchPanel);
        add(spinner);
        add(optionPanel);

        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    private void optionPanelLoad() {
        final FlatJRadioButton distanceRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "distance");
        final FlatJRadioButton apiedRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "à pied");
        final FlatJRadioButton entempsRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "temps ");

        final ButtonGroup groupe = new ButtonGroup();
        distanceRadioButton.setSelected(true);
        groupe.add(distanceRadioButton);
        groupe.add(apiedRadioButton);
        groupe.add(entempsRadioButton);

        optionPanel.add(distanceRadioButton);
        optionPanel.add(apiedRadioButton);
        optionPanel.add(entempsRadioButton);
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
