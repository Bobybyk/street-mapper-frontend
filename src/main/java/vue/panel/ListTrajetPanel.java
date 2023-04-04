package vue.panel;

import app.map.Section;
import app.server.data.Route;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * ListTrajetPanel est un jpanel
 * Dans ce jpanel on affiche la liste des trajet disponible
 *
 */

public class ListTrajetPanel extends JPanel implements MouseListener {


    private JLabel htmlJLabel;

    ListTrajetPanel(Route route) {
        setBorder(BorderFactory.createLineBorder(new Color(100, 166, 74)));
        setBackground(new Color(169,223, 191));
        final Dimension d = new Dimension(650, 150);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        StringBuilder trajetString = new StringBuilder();
        for (Section section : route.getPathDistOpt()) trajetString.append("<li>").append(section.toString()).append("</li>");
        this.htmlJLabel = new JLabel("<html>Mon trajet:" +
                "<ul>" +
                trajetString +
                "</html>");
        this.htmlJLabel.setFont(BuilderJComposant.lemontRegularFont(14f));
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
        this.setBackground(new Color(125, 206, 160));
        this.setForeground(Color.white);
        revalidate();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        this.setBackground(new Color(169, 223, 191));
        this.setForeground(Color.black);
        revalidate();
        repaint();
    }
}
