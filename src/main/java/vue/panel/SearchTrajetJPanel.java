package vue.panel;

import app.map.Time;
import app.server.data.ErrorServer;
import app.server.data.Route;
import controller.Controller;
import data.DataList;
import vue.composant.FlatJScrollPane;
import vue.composant.FlatJTextField;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Locale;

/**
 * SearchTrajetJPanel est un jpanel
 * c'est ici qu'on affiche le resultat de recherche
 */

public class SearchTrajetJPanel extends JPanel {

    private final FlatJTextField stationDepartList, stationArriveList;
    private final JButton valideJbutton;
    private final JScrollPane paneScroll;
    private final JPanel resultPanel, researchPanel;
    private Timer timer;

    SearchTrajetJPanel(Controller controler){
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = BuilderJComposant.createPanelBoxLayoutVertical();
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

        paneScroll = new FlatJScrollPane(resultPanel);

        this.timer = new Timer(2000, e -> {
            resultPanel.removeAll();
            Serializable serverData = DataList.route;
            if(serverData instanceof Route){
                resultPanel.add(new ListTrajetPanel((Route) serverData));
            }else if(serverData instanceof ErrorServer){
                resultPanel.add(new JLabel("Erreur: " + ((ErrorServer) serverData).getError().toLowerCase()));
            }else{
                resultPanel.add(new JLabel("Erreur"));
                System.out.println("Erreur");
            }
            repaint();
            revalidate();
            timer.stop();
        });

        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            resultPanel.removeAll();
            resultPanel.add(BuilderJComposant.createJLabelStyle("Recherche en attente ...", 18f, Color.black));
            repaint();
            revalidate();
            String depart = stationDepartList.getText();
            String arrive = stationArriveList.getText();
            controler.sendRequestRoute("ROUTE;"+depart+";"+arrive);
            timer.start();
        });
        add(researchPanel);
        add(paneScroll);
        setBackground(new Color(184,223,168));
    }

}
