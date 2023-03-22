package app.vue.panel;

import javax.swing.*;

public class RootJPanel extends JPanel {


    private final JPanel rootJPanel;
    private final MenuSelectionJPanel selectionJPanel;

    public RootJPanel(){
        rootJPanel = new JPanel();
        rootJPanel.add(new SearchTrajetJPanel());
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        selectionJPanel = new MenuSelectionJPanel(this);
        add(selectionJPanel);
        add(rootJPanel);
    }

    public void updateRootPanel(JComponent rootJPanel){
        this.rootJPanel.removeAll();
        this.rootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}
