package app.vue.panel;

import app.vue.utils.BuilderJComposant;
import map.Trajet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListTrajetPanel extends JPanel implements MouseListener {

    /**
     * ListTrajetPanel est un jpanel
     * Dans ce jpanel on affiche la liste des trajet disponible
     *
     */
    private JLabel htmlJLabel;

    ListTrajetPanel(Trajet trajet) {
        setBorder(BorderFactory.createLineBorder(new Color(100, 166, 74)));
        setBackground(new Color(184, 223, 168));
        final Dimension d = new Dimension(650, 150);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        StringBuilder trajetString = new StringBuilder();
        for (String st : trajet.getTrajet()) trajetString.append("<li>").append(st).append("</li>");
        this.htmlJLabel = new JLabel("<html>Mon trajet: Station " + trajet.getDepart() + " à Station " + trajet.getArrive() + "<br>" +
                "<ul>" +
                trajetString +
                "</html>");//trajet.getDepart() +" à " + trajet.getArrive() + " <html><br>test</html>");
        htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(14f));
        this.add(htmlJLabel);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        this.setBackground(new Color(100, 166, 74));
        this.setForeground(Color.white);
        revalidate();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setBackground(new Color(184, 223, 168));
        this.setForeground(Color.black);
        revalidate();
        repaint();
    }
}
