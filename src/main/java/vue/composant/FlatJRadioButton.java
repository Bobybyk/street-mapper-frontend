package vue.composant;

import javax.swing.*;
import java.awt.*;

public class FlatJRadioButton extends JRadioButton {

    public FlatJRadioButton(String imagePath, String text){
        this.setText(text);
        this.setForeground(Color.GRAY);
        this.setIcon(new ImageIcon(imagePath));
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()) setForeground(Color.GREEN);
        else this.setForeground(Color.GRAY);
    }
}
