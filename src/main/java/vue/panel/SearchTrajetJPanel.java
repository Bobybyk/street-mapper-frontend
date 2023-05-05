package vue.panel;

import controller.Controller;
import server.data.SuggestionStations;
import vue.composant.FlatComboBox;
import vue.composant.FlatJRadioButton;
import vue.composant.FlatJScrollPanel;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatComboBox stationDepartList;
    private final FlatComboBox  stationArriveList;
    private final JPanel resultPanel;
    private final JPanel typeDeplacementPanel;
    private final JPanel optionPanel;
    private FlatJRadioButton sectionPied;
    private FlatJRadioButton distanceRadioButton;
    private FlatJRadioButton entempsRadioButton;
    private Date date;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setPreferredSize(new Dimension(650, 700));
        this.stationArriveList = arrivalBox;
        this.stationDepartList = startBox;
        this.resultPanel = researchPanelB;

        this.typeDeplacementPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded(new Dimension(160, 60));
        this.optionPanel = BuilderJComposant.createPanelBoxLayoutHorizontal(Props.OPTION_RECHERCHE);
        optionPanel.setOpaque(false);
        optionPanelLoad();
        sectionAPied();
        datePanelLoad();

        final JPanel researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded(new Dimension(645, 100));
        final JButton valideJbutton = BuilderJComposant.createJButton(Props.VALIDER);
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.setOpaque(true);

        stationArriveList.requestInitComboBox(controler, SuggestionStations.SuggestionKind.ARRIVAL);
        stationDepartList.requestInitComboBox(controler, SuggestionStations.SuggestionKind.DEPART);

        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            if (((stationDepartList.getTextField().getText().isBlank() || stationDepartList.getTextField().getText().isEmpty()) || stationDepartList.getTextField().getText().equalsIgnoreCase(Props.DEPART) ||
                    stationArriveList.getTextField().getText().isBlank() || stationArriveList.getTextField().getText().isEmpty() || stationArriveList.getTextField().getText().equalsIgnoreCase(Props.ARRIVE))) {
                resultPanel.add(BuilderJComposant.createJLabelStyle(Props.CHAMPS_INCORRECT, 18f, Color.RED));
            } else {
                resultPanel.add(BuilderJComposant.createJLabelStyle(Props.RECHERCHE_EN_COURS, 18f, Color.black));
                String typeTrajet = "DISTANCE";
                if (distanceRadioButton.isSelected()) typeTrajet = "DISTANCE";
                else if (entempsRadioButton.isSelected()) typeTrajet = "TIME";
                controler.sendRequestRoute(stationDepartList.getTextField().getText(), stationArriveList.getTextField().getText(), typeTrajet, sectionPied.isSelected(), date);
                stationArriveList.clearField();
                stationDepartList.clearField();
            }
            repaint();
            revalidate();
        });
        final JScrollPane paneScroll = new FlatJScrollPanel(resultPanel);
        paneScroll.setBorder(BorderFactory.createEmptyBorder());
        add(researchPanel);
        add(optionPanel);
        add(paneScroll);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));
    }

    private void datePanelLoad() {
        final JPanel panelHeure = BuilderJComposant.createPanelBoxLayoutHorizontal();
        SpinnerDateModel model = new SpinnerDateModel();
        Calendar calendar = Calendar.getInstance();
        model.setValue(calendar.getTime());
        model.setCalendarField(Calendar.HOUR_OF_DAY); // Définir le champ calendrier pour modifier uniquement les heures
        JSpinner.DateEditor editor = new JSpinner.DateEditor(new JSpinner(model), "HH:mm");
        editor.getTextField().setEditable(false);
        editor.getTextField().setBackground(java.awt.Color.WHITE);
        editor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        final JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(60, 50));
        spinner.setMaximumSize(new Dimension(60, 50));
        spinner.setMinimumSize(new Dimension(60, 50));
        spinner.setEditor(editor);
        date = model.getDate();
        spinner.addChangeListener(e -> date = model.getDate());
        panelHeure.add(new JLabel(Props.DEPART_A));
        panelHeure.add(spinner);
        panelHeure.setOpaque(false);
        optionPanel.add(panelHeure);
    }

    private void optionPanelLoad() {
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        distanceRadioButton = BuilderJComposant.createJRadioButton(Props.DISTANCE);
        entempsRadioButton = BuilderJComposant.createJRadioButton(Props.TEMPS);

        final ButtonGroup groupe = new ButtonGroup();
        distanceRadioButton.setSelected(true);
        groupe.add(distanceRadioButton);
        groupe.add(entempsRadioButton);

        typeDeplacementPanel.add(distanceRadioButton);
        typeDeplacementPanel.add(entempsRadioButton);
        panelTypeTrajet.add(new JLabel(Props.TYPE_TRAJET));
        panelTypeTrajet.add(typeDeplacementPanel);
        panelTypeTrajet.setOpaque(false);
        optionPanel.add(panelTypeTrajet);
    }

    private void sectionAPied() {
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        sectionPied = BuilderJComposant.createJRadioButton(Props.NON);
        sectionPied.addActionListener(e -> {
            if (sectionPied.isSelected()) sectionPied.setText(Props.OUI);
            else sectionPied.setText(Props.NON);
        });
        panelTypeTrajet.add(new JLabel(Props.SECTION_A_PIED));
        panelTypeTrajet.add(sectionPied);
        panelTypeTrajet.setOpaque(false);
        optionPanel.add(panelTypeTrajet);
    }


}
