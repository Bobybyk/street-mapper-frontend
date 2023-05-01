package vue.composant;

import javax.swing.*;
import java.awt.*;

public class FlatJRadioButton extends JRadioButton {

    private Color hover, release;

    public FlatJRadioButton(String text){
        this.setText(text);
        this.setForeground(Color.GRAY);
        this.setIcon(new ImageIcon(""));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        hover = Color.GREEN;
        release = Color.gray;
    }

    public FlatJRadioButton(String text, Color hover, Color release){
        this(text);
        this.hover = hover;
        this.release = release;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()) setForeground(hover);
        else this.setForeground(release);
    }
}
