package vue.panel;

import app.server.data.SuggestionStations;
import controller.Controller;
import vue.composant.FlatJRadioButton;
import vue.composant.FlatComboBox;
import vue.composant.FlatJScrollPane;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;

import static vue.utils.Props.depart;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private FlatJRadioButton sectionPied, distanceRadioButton, entempsRadioButton;
    private final FlatComboBox stationDepartList, stationArriveList;
    private final JPanel resultPanel, typeDeplacementPanel, optionPanel;
    private Date date;

    SearchTrajetJPanel(Controller controler, ResearchPanel researchPanelB, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setPreferredSize(new Dimension(650, 700));
        this.stationArriveList = arrivalBox;
        this.stationDepartList = startBox;
        this.resultPanel = researchPanelB;
        this.typeDeplacementPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded(new Dimension(160, 60));
        this.optionPanel = BuilderJComposant.createPanelBoxLayoutHorizontal(Props.optionRecherche);
        optionPanel.setOpaque(false);
        optionPanelLoad();
        sectionAPied();
        datePanelLoad();

        final JPanel researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontalRounded(new Dimension(650, 150));
        final JButton valideJbutton = BuilderJComposant.createJButton(Props.valider);
        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.setOpaque(true);

        setJcomboBox(controler, stationArriveList, SuggestionStations.SuggestionKind.ARRIVAL);
        setJcomboBox(controler, stationDepartList, SuggestionStations.SuggestionKind.DEPART);

        valideJbutton.addActionListener(e -> {
            resultPanel.removeAll();
            if (((stationDepartList.getTextField().getText().isBlank() || stationDepartList.getTextField().getText().isEmpty()) || stationDepartList.getTextField().getText().equalsIgnoreCase(depart) ||
                    stationArriveList.getTextField().getText().isBlank() || stationArriveList.getTextField().getText().isEmpty() || stationArriveList.getTextField().getText().equalsIgnoreCase(Props.arrive))) {
                resultPanel.add(BuilderJComposant.createJLabelStyle(Props.champsIncorrect, 18f, Color.RED));
            } else {
                resultPanel.add(BuilderJComposant.createJLabelStyle(Props.rechercheEnCours, 18f, Color.black));
                String typeTrajet = "DISTANCE";
                if (distanceRadioButton.isSelected()) typeTrajet = "DISTANCE";
                else if (entempsRadioButton.isSelected()) typeTrajet = "TIME";
                controler.sendRequestRoute(stationDepartList.getTextField().getText(), stationArriveList.getTextField().getText(), typeTrajet, sectionPied.isSelected(), date);
            }
            repaint();
            revalidate();
        });
        final JScrollPane paneScroll = new FlatJScrollPane(resultPanel);
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
        editor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        final JSpinner spinner = new JSpinner(model);
        spinner.setPreferredSize(new Dimension(60, 50));
        spinner.setMaximumSize(new Dimension(60, 50));
        spinner.setMinimumSize(new Dimension(60, 50));
        spinner.setEditor(editor);
        date = model.getDate();
        spinner.addChangeListener(e -> date = model.getDate());
        panelHeure.add(new JLabel(Props.departA));
        panelHeure.add(spinner);
        panelHeure.setOpaque(false);
        optionPanel.add(panelHeure);
    }

    private void optionPanelLoad() {
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        distanceRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, Props.distance);
        entempsRadioButton = BuilderJComposant.createJRadioButton(Props.iconPathSearch, Props.temps);

        final ButtonGroup groupe = new ButtonGroup();
        distanceRadioButton.setSelected(true);
        groupe.add(distanceRadioButton);
        groupe.add(entempsRadioButton);

        typeDeplacementPanel.add(distanceRadioButton);
        typeDeplacementPanel.add(entempsRadioButton);
        panelTypeTrajet.add(new JLabel(Props.typeTrajet));
        panelTypeTrajet.add(typeDeplacementPanel);
        panelTypeTrajet.setOpaque(false);
        optionPanel.add(panelTypeTrajet);
    }

    private void sectionAPied() {
        final JPanel panelTypeTrajet = BuilderJComposant.createPanelBoxLayoutHorizontal();
        sectionPied = BuilderJComposant.createJRadioButton(Props.iconPathSearch, Props.non);
        sectionPied.addActionListener(e -> {
            if (sectionPied.isSelected()) sectionPied.setText(Props.oui);
            else sectionPied.setText(Props.non);
        });
        panelTypeTrajet.add(new JLabel(Props.sectionAPied));
        panelTypeTrajet.add(sectionPied);
        panelTypeTrajet.setOpaque(false);
        optionPanel.add(panelTypeTrajet);
    }

    private void setJcomboBox(Controller controler, FlatComboBox field, SuggestionStations.SuggestionKind depart) {
        field.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String word = ((JTextField) field.getEditor().getEditorComponent()).getText();
                char c = e.getKeyChar();
                if (Character.isLetterOrDigit(c)) {
                    field.showPopup();
                    Timer te = new Timer();
                    te.purge();
                    te.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            controler.sendRequestSearch(word, depart);
                            cancel();
                        }
                    }, 0, 400);
                }
            }
        });
    }

}
