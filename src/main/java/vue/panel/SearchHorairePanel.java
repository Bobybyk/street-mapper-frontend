package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class SearchHorairePanel extends JPanel {

    private final FlatComboBox stationRecherche;

    private final JButton valideJbutton;
    private final JPanel resultPanel, researchPanel;
    private Date date;

    SearchHorairePanel(Controller controler, FlatComboBox stationRecherche){
        setPreferredSize(new Dimension(650, 700));
        this.resultPanel = BuilderJComposant.createPanelBoxLayoutVertical();
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationRecherche = stationRecherche;
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationRecherche);
        datePanelLoad();
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            String station = stationRecherche.getTextField().getText();
            controler.sendRequestHoraire(station,  date);
        });
        add(researchPanel);
        add(resultPanel);
        setBackground(new Color(184,223,168));
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
        researchPanel.add(panelHeure);
    }

}