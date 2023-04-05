package vue.panel;

import app.map.Time;
import app.server.data.ErrorServer;
import app.server.data.Route;
import controller.Controller;
import data.DataList;
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

    private final JPanel resultPanel, researchPanel;


    private Timer timer;

    SearchTrajetJPanel(Controller controler){
        setPreferredSize(new Dimension(650, 500));
        this.resultPanel = BuilderJComposant.createPanelBoxLayoutVertical();
        this.researchPanel = BuilderJComposant.createPanelBoxLayoutHorizontal();
        this.stationArriveList = BuilderJComposant.createFlatJTextField(Props.depart);
        this.stationDepartList = BuilderJComposant.createFlatJTextField(Props.arrive);
        this.valideJbutton = BuilderJComposant.createJButton(Props.valider);

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
            revalidate();
            repaint();
            timer.stop();
        });

        researchPanel.setBackground(Color.getHSBColor(23, 312, 3));
        researchPanel.add(stationDepartList);
        researchPanel.add(stationArriveList);
        researchPanel.add(valideJbutton);
        valideJbutton.addActionListener(e ->{
            String depart = stationDepartList.getText();
            String arrive = stationArriveList.getText();
            controler.sendRequestRoute("ROUTE;"+depart+";"+arrive);
            timer.start();
            resultPanel.add(new JLabel("Recherche en attente ..."));
        });
        add(researchPanel);
        add(resultPanel);
        setBackground(new Color(184,223,168));
    }

}
