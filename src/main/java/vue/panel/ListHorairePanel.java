package vue.panel;

import app.map.Section;
import app.map.StationInfo;
import app.server.data.DepartureTimes;
import app.server.data.Route;
import app.server.data.StationTime;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class ListHorairePanel extends JPanel {

    private final JLabel htmlJLabel;

    public ListHorairePanel(DepartureTimes separtureTimes) {
        setBorder(BorderFactory.createEmptyBorder());
        setBackground(new Color(238, 238, 238));
        StringBuilder trajetString = new StringBuilder();
        for (StationTime stationTime : separtureTimes.getTimes())
            trajetString.append("Station: ").append(stationTime.getStation()).append(stationTime.getTime().hour() + ":" + stationTime.getTime().second());
        this.htmlJLabel = new JLabel("<html>Listes des horaires " + "<ul>"+trajetString+"</html>");
        this.htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(15f));
        this.add(htmlJLabel);
    }
}
