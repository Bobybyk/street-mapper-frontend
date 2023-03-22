package app.vue.composant;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class FlatJButton extends JButton implements MouseListener, MouseMotionListener {

    private Graphics graphics;

    public FlatJButton(String name){
        this.graphics = super.getGraphics();
        setText(name);
        setPreferredSize(new Dimension(250, 100));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBackground(new Color(195, 255, 104));
        setFont(BuilderJComposant.lemontRegularFont(20f));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(23,34,23,30));
        g.drawLine(this.getWidth(), this.getHeight(), this.getWidth()+10, this.getHeight()+10);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }
}
