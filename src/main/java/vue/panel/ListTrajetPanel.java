package vue.panel;

import vue.composant.FlatJButton;
import vue.utils.BuilderJComposant;
import vue.utils.Props;

import javax.swing.*;

import server.data.Route;
import server.map.Section;

import java.awt.*;

/**
 * ListTrajetPanel est un jpanel
 * Dans ce jpanel on affiche la liste des trajet disponible
 *
 */

public class ListTrajetPanel extends JPanel {


    public ListTrajetPanel(Route route) {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(238, 238, 238));
        StringBuilder trajetString = new StringBuilder();

        if(!route.getPathDistOpt().isEmpty()){
            for (Section section :  Section.sectionsToTrajet(route.getPathDistOpt())) {
                trajetString.append("<li>").append(section).append("</li>");
            }
        }else trajetString.append(Props.DESTINATION);
        JLabel htmlJLabel = new JLabel("<html>" + Props.MON_TRAJET + "<ul>" + trajetString + "<br>" + Props.UNIQUEMENT_SECTIONS +".</html>");
        htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(16f));
        FlatJButton voirMap = new FlatJButton(Props.BUTTON_VOIR_MAP, new Dimension(400, 150));
        MapJPanel map = RootJPanel.getInstanceMap();
        voirMap.addActionListener(e->{
           map.clearPoint();
            map.addPoint(route.getPathDistOpt().get(0).getStart());
            for (Section section : route.getPathDistOpt()) {
                map.addPoint(section.getArrival());
            }
        });
        this.add(htmlJLabel);
        this.add(voirMap);
    }

}
