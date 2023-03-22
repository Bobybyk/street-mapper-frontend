package app.vue;

import app.vue.panel.RootJPanel;
import app.vue.utils.BuilderJComposant;

import javax.swing.*;

public class MainWindowJFrame extends JFrame {


    public MainWindowJFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setContentPane(new RootJPanel());
        setAlwaysOnTop(true);
        setTitle("Application de transport | GLA 2023");
      //  setResizable(false);
        pack();
    }

}
