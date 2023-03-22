package app.vue.panel;

import app.vue.utils.BuilderJComposant;
import map.Trajet;

import javax.swing.*;
import java.awt.*;

public class HistoryTrajetJPanel extends JPanel {

    HistoryTrajetJPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(232, 246, 243));
        setPreferredSize(new Dimension(600, 500));
        for(int i = 0; i <15; i++){
            add(new ListTrajetPanel(new Trajet()));
        }
    }



    public class ListTrajetPanel extends JPanel{

        private JLabel htmlJLabel;

        ListTrajetPanel(Trajet trajet){
            setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 10));
            setBackground(new Color(208, 236, 231));
            this.htmlJLabel =  new JLabel( "<html><b><u>T</u>wo</b><br>lines</html>");//trajet.getDepart() +" à " + trajet.getArrive() + " <html><br>test</html>");
            htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(22f));
            this.add(htmlJLabel);
            StringBuilder trajetString = new StringBuilder();
            for(String st: trajet.getTrajet()) trajetString.append(st).append("->");

        }

    }
}
