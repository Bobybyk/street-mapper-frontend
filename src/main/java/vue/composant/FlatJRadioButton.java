package vue.composant;

import javax.swing.*;
import java.awt.*;

public class FlatJRadioButton extends JRadioButton {

    private final Color hover;

    private final Color release;

    public FlatJRadioButton(String text, Color hover, Color release) {
        this.setText(text);
        this.setForeground(Color.GRAY);
        this.setIcon(new ImageIcon(""));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.hover = hover;
        this.release = release;

    }

    public FlatJRadioButton(String text){
        this(text, Color.GREEN, Color.gray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()) setForeground(hover);
        else this.setForeground(release);
    }
}
