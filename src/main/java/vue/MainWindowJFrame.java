package vue;

import controller.Controller;
import vue.panel.RootJPanel;

import javax.swing.*;

public class MainWindowJFrame extends JFrame {

    public MainWindowJFrame(Controller controller){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(new RootJPanel(controller));
        setAlwaysOnTop(true);
        setTitle("Application de transport | GLA 2023");
        setResizable(false);
        pack();
    }

}