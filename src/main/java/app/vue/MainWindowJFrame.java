package app.vue;

import javax.swing.*;
import java.awt.*;

public class MainWindowJFrame extends JFrame {


    public MainWindowJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(new RootJPanel());
        setResizable(false);
        pack();
    }

}
