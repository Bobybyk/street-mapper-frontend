package vue.composant;

import app.App;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

public class FlatJButton extends JButton implements MouseListener, MouseMotionListener {


    /**
     * FlatJButton est une class Jbutton
     * avec des parametres graphiques déjà fait pour facilité
     * l'implementation graphique
     */

    public FlatJButton(String name, Dimension d){
        setText(name);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBackground(new Color(127, 177, 50));
        setFont(BuilderJComposant.lemontRegularFont(20f));
        setFocusPainted(false);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public FlatJButton(String name){
      this(name, new Dimension(150, 100));
    }



    public FlatJButton(String name, String path){
        this(name);
        URL url = App.class.getResource(path);
        if (url != null) this.setIcon(new ImageIcon(url));
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(23,34,23,30));
        g.drawLine(this.getWidth(), this.getHeight(), this.getWidth()+10, this.getHeight()+10);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        // Non utilisée
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        // Non utilisée
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        // Non utilisée
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        setBackground(new Color(127, 177, 128));
        setForeground(new Color(127,178,49));
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        setBackground(new Color(127, 177, 30));
        setForeground(Color.BLACK);
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        // Non utilisée
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        // Non utilisée
    }
}
