package app.vue.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class BuilderJComposant {

    public static JButton createJButton(String buttonName){
        final JButton jButton = new JButton(buttonName);
        jButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        jButton.setFont(new Font("Serif",Font.BOLD,20));
        jButton.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent focusEvent) {
                jButton.getGraphics().setPaintMode();
                jButton.getGraphics().drawLine(10, 10, 15, 15);
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                jButton.getGraphics().setXORMode(Color.red);
                jButton.getGraphics().drawLine(10, 10, 15, 15);
            }
        });
        return jButton;
    }

}
