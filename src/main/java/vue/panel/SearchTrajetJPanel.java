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
    private final JPanel resultPanel, researchPanel, typeDeplacementPanel, optionPanel;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB) {
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = researchPanelB;
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.typeDeplacementPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.optionPanel = BuilderJComposant.createPanelBoxLayoutHorizontal("Option de recherche");
        optionPanel.setOpaque(false);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);


        paneScroll = new FlatJScrollPane(resultPanel);

        optionPanelLoad();
        sectionAPied();
        datePanelLoad();

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
        add(optionPanel);
        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    private void datePanelLoad() {
        final JPanel panelHeure = BuilderJComposant.createPanelBoxLayoutHorizontal();

        // Créez une instance de la classe SpinnerDateModel
        SpinnerDateModel model = new SpinnerDateModel();
        Calendar calendar = Calendar.getInstance();
        model.setValue(calendar.getTime());
        model.setCalendarField(Calendar.HOUR_OF_DAY); // Définir le champ calendrier pour modifier uniquement les heures

        // Créer un éditeur personnalisé
        JSpinner.DateEditor editor = new JSpinner.DateEditor(new JSpinner(model), "HH:mm");
        editor.getTextField().setEditable(false);
        editor.getTextField().setBackground(java.awt.Color.WHITE);
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);

        // Créer un Spinner
        JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(60, 50));
        spinner.setMaximumSize(new Dimension(60, 50));
        spinner.setMinimumSize(new Dimension(60, 50));
        spinner.setEditor(editor);
        // Ajoutez le compsant JSpinner à la fenêtre
        panelHeure.add(new JLabel("Depart à :"));
        panelHeure.add(spinner);
        optionPanel.add(panelHeure);
    }

    private void optionPanelLoad() {
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        final FlatJRadioButton distanceRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "Distance");
       // final FlatJRadioButton apiedRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "Section à pied");
        final FlatJRadioButton entempsRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "Temps ");

        final ButtonGroup groupe = new ButtonGroup();
        distanceRadioButton.setSelected(true);
        groupe.add(distanceRadioButton);
      //  groupe.add(apiedRadioButton);
        groupe.add(entempsRadioButton);

        typeDeplacementPanel.add(distanceRadioButton);
       // typeDeplacementPanel.add(apiedRadioButton);
        typeDeplacementPanel.add(entempsRadioButton);
        panelTypeTrajet.add(new JLabel(Props.typeTrajet));
        panelTypeTrajet.add(typeDeplacementPanel);
        optionPanel.add(panelTypeTrajet);
    }

    private void sectionAPied(){
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        final FlatJRadioButton sectionPied = BuilderJComposant.createJRadioButton(Props.iconPathSearch, "Non");
        sectionPied.addActionListener(e ->{
            if(sectionPied.isSelected()) sectionPied.setText("Oui");
            else sectionPied.setText("Non");
        });
        panelTypeTrajet.add(new JLabel(Props.sectionAPied));
        panelTypeTrajet.add(sectionPied);
        optionPanel.add(panelTypeTrajet);
    }

    public JPanel getResearchPanel() {
        return researchPanel;
    }
}
