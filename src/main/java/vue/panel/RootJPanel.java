package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;

import javax.swing.*;

/**
 * RootJPanel est la jpanel principal ou les elements vont être
 * changé lors d'un événement de clique
 */

public class RootJPanel extends JPanel {

    private final JPanel verticalRootJPanel;
    private final JPanel attRootJPanel;
    private final MenuSelectionJPanel selectionJPanel;
    private static MapJPanel map = null;

    public RootJPanel(Controller controller, ResearchPanel panel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        attRootJPanel = new JPanel();
        map = (new MapJPanel(startBox, arrivalBox));
        verticalRootJPanel = new JPanel();
        verticalRootJPanel.setLayout(new BoxLayout(verticalRootJPanel, BoxLayout.Y_AXIS));
        attRootJPanel.add(new SearchTrajetJPanel(controller, panel, startBox, arrivalBox));
        selectionJPanel = new MenuSelectionJPanel(controller,this, panel, startBox, arrivalBox);
        verticalRootJPanel.add(attRootJPanel);
        verticalRootJPanel.add(selectionJPanel);
        add(verticalRootJPanel);
        add(map);
    }

    public static MapJPanel getInstanceMap() {
        return map;
    }

    public void updateRootPanel(JComponent rootJPanel) {
        this.attRootJPanel.removeAll();
        this.attRootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}
