package vue.panel;

import app.map.Section;
import app.server.data.Route;
import vue.composant.FlatJButton;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

/**
 * ListTrajetPanel est un jpanel
 * Dans ce jpanel on affiche la liste des trajet disponible
 *
 */

public class ListTrajetPanel extends JPanel {


    public ListTrajetPanel(Route route) {
        setBorder(BorderFactory.createEmptyBorder());
        setLayout(new GridLayout(2, 1));
        setBackground(new Color(238, 238, 238));
        StringBuilder trajetString = new StringBuilder();
        if(route.getPathDistOpt().size() > 0){
            for (Section section : route.getPathDistOpt()) trajetString.append("Station: ").append("<li>").append(section).append("</li>");
        }else trajetString.append("Vous etes déjà à destination");
        JLabel htmlJLabel = new JLabel("<html>Mon trajet:" + "<ul>" + trajetString + "</html>");
        htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(18f));
        FlatJButton voirMap = new FlatJButton("Voir sur la map");
        MapJPanel map = RootJPanel.getInstanceMap();
        voirMap.addActionListener(e->{
           map.clearPoint();
            map.addPoint(route.getPathDistOpt().getFirst().getStart());
            for (Section section : route.getPathDistOpt()) {
                map.addPoint(section.getArrival());
            }
        });
        this.add(htmlJLabel);
        this.add(voirMap);
    }

}
