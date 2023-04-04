package vue.panel;

import controller.Controller;

import javax.swing.*;

/**
 * RootJPanel est la jpanel principal ou les elements vont être
 * changé lors d'un événement de clique
 */

public class RootJPanel extends JPanel {

    private final JPanel rootJPanel;
    private final MenuSelectionJPanel selectionJPanel;

    public RootJPanel(Controller controller) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        rootJPanel = new JPanel();
        rootJPanel.add(new SearchTrajetJPanel(controller));
        selectionJPanel = new MenuSelectionJPanel(controller,this);
        add(rootJPanel);
        add(selectionJPanel);
    }

    public void updateRootPanel(JComponent rootJPanel) {
        this.rootJPanel.removeAll();
        this.rootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}