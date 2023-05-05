package vue.panel;

import controller.Controller;
import server.data.SuggestionStations;
import vue.composant.FlatComboBox;

import vue.composant.FlatJScrollPanel;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class SearchHorairePanel extends JPanel {

    private final JButton valideJbutton;
    private final JPanel resultPanel;
    private final JPanel researchPanel;
    private Date date;

    SearchHorairePanel(Controller controler, ResearchPanel researchPanelB, FlatComboBox stationRecherche){
        setPreferredSize(new Dimension(650, 700));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.resultPanel = researchPanelB;
        this.researchPanel =  BuilderJComposant.createPanelBoxLayoutHorizontalRounded(new Dimension(565, 100));
        stationRecherche.requestInitComboBox(controler, SuggestionStations.SuggestionKind.DEPART);
        this.valideJbutton = BuilderJComposant.createJButton(Props.VALIDER);
        valideJbutton.setOpaque(true);
        researchPanel.setOpaque(true);
        researchPanel.add(stationRecherche);
        researchPanel.setBackground(Color.WHITE);
        datePanelLoad();
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            resultPanel.removeAll();
            String station = stationRecherche.getTextField().getText();
            controler.sendRequestHoraire(station,  date);
            resultPanel.add(new JLabel(Props.RECHERCHE_EN_COURS));
            resultPanel.repaint();
            resultPanel.revalidate();
        });

        final JScrollPane paneScroll = new FlatJScrollPanel(resultPanel);
        paneScroll.setBorder(BorderFactory.createEmptyBorder());
        add(researchPanel);
        add(paneScroll);
    }

    private void datePanelLoad() {
        final JPanel panelHeure = BuilderJComposant.createPanelBoxLayoutHorizontal();
        SpinnerDateModel model = new SpinnerDateModel();
        Calendar calendar = Calendar.getInstance();
        model.setValue(calendar.getTime());
        model.setCalendarField(Calendar.HOUR_OF_DAY);
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
        panelHeure.add(new JLabel(Props.HORAIRE_A));
        panelHeure.add(spinner);
        panelHeure.setOpaque(false);
        panelHeure.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        researchPanel.add(panelHeure);
    }

}