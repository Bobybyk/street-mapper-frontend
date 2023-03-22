package app.vue.panel;

import app.vue.composant.FlatSearchBar;

import javax.swing.*;
import java.awt.*;

public class SearchTrajetJPanel extends JPanel {

    SearchTrajetJPanel(){
        add(new FlatSearchBar(), BorderLayout.NORTH);
        setBackground(Color.BLUE);
        setPreferredSize(new Dimension(600, 500));
    }
}
