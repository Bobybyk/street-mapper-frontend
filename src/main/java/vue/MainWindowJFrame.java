package vue;

import controller.Controller;
import vue.panel.ResearchPanel;
import vue.panel.RootJPanel;

import javax.swing.*;

public class MainWindowJFrame extends JFrame {

    public MainWindowJFrame(Controller controller, ResearchPanel panel){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(new RootJPanel(controller, panel));
        setAlwaysOnTop(true);
        setTitle("Application de transport | GLA 2023");
        setResizable(false);
        pack();
    }

}
