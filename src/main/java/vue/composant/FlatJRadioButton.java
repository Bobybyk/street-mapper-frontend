package vue.composant;

import javax.swing.*;
import java.awt.*;

public class FlatJRadioButton extends JRadioButton {

    public FlatJRadioButton(String imagePath, String text){
        this.setText(text);
        this.setForeground(Color.GRAY);
        this.setIcon(new ImageIcon(imagePath));
}


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isSelected()){
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.setColor(Color.green);
            graphics2D.drawRect(0,0,this.getHeight(), this.getWidth());
        }
    }
}
