package vue.panel;

import app.server.data.DepartureTimes;
import app.server.data.StationTime;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class ListHorairePanel extends JPanel {

    public ListHorairePanel(DepartureTimes separtureTimes) {
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(238, 238, 238));
        StringBuilder trajetString = new StringBuilder();
        System.out.println(separtureTimes.getTimes().size());
        for (StationTime stationTime : separtureTimes.getTimes())
            trajetString.append("<li>").append(stationTime).append("<li>");
        JLabel htmlJLabel = new JLabel("<html>Listes des horaires " + "<ul>" + trajetString + "<ul>" + "</html>");
        htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(15f));
        add(htmlJLabel);
    }
}
