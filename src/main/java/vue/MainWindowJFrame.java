package vue;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.panel.ResearchPanel;
import vue.panel.RootJPanel;

import javax.swing.*;

public class MainWindowJFrame extends JFrame {

    public MainWindowJFrame(Controller controller, ResearchPanel panel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(new RootJPanel(controller, panel, startBox, arrivalBox));
        setTitle("Application de transport | GLA 2023");
        setResizable(false);
        pack();
    }

}
