package vue;

import controller.Controller;
import vue.composant.FlatComboBox;
import vue.panel.ResearchPanel;
import vue.panel.RootJPanel;
import vue.utils.Props;

import javax.swing.*;

public class MainWindowJFrame extends JFrame {

    public MainWindowJFrame(Controller controller, ResearchPanel panel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setIconImage((new ImageIcon(Props.logo_icon)).getImage());
        setContentPane(new RootJPanel(controller, panel, startBox, arrivalBox));
        setTitle("Application de transport | GLA 2023");
        setResizable(false);
        pack();
    }

}
