package vue.panel;

import app.map.Coordinate;
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


    private final JLabel htmlJLabel;

    public ListTrajetPanel(Route route) {
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(238, 238, 238));
        StringBuilder trajetString = new StringBuilder();
        if(route.getPathDistOpt().size() > 0){
            for (Section section : route.getPathDistOpt()) trajetString.append("Station: ").append("<li>").append(section).append("</li>");
        }else trajetString.append("Vous etes déjà à destination");
        this.htmlJLabel = new JLabel("<html>Mon trajet:"+"<ul>"+trajetString+"</html>");
        this.htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(14f));
        FlatJButton voirMap = new FlatJButton("Voir sur la map");
        voirMap.addActionListener(e->{
           /* map.clearPoint();
            for (Section section : route.getPathDistOpt()) {
                Coordinate coordinate = section.getArrival().getCoordinate();
                map.addPoint(coordinate.getLatitude(), coordinate.getLongitude());
            }*/
        });
        this.add(htmlJLabel);
    }

}
