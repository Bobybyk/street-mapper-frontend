package app.vue;

import javax.swing.*;

public class RootJPanel extends JPanel {


    private JPanel rootJPanel;
    private MenuSelectionJPanel selectionJPanel;

    RootJPanel(){
        rootJPanel = new JPanel();
        rootJPanel.add(new SearchTrajetJPanel());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        selectionJPanel = new MenuSelectionJPanel(this);
        add(selectionJPanel);
        add(rootJPanel);
    }

    public void updateRootPanel(JPanel rootJPanel){
        this.rootJPanel.removeAll();
        this.rootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}
