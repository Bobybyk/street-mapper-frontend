package vue.panel;

import controller.Controller;
import vue.composant.FlatComboBox;

import javax.swing.*;

/**
 * RootJPanel est la jpanel principal ou les elements vont être
 * changé lors d'un événement de clique
 */

public class RootJPanel extends JPanel {

    private final JPanel verticalRootJPanel, rootJPanel;
    private final MenuSelectionJPanel selectionJPanel;
    private static MapJPanel map = null;

    public RootJPanel(Controller controller, ResearchPanel panel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        rootJPanel = new JPanel();
        map = (new MapJPanel(startBox, arrivalBox));
        verticalRootJPanel = new JPanel();
        verticalRootJPanel.setLayout(new BoxLayout(verticalRootJPanel, BoxLayout.Y_AXIS));
        rootJPanel.add(new SearchTrajetJPanel(controller, map, panel, startBox, arrivalBox));
        selectionJPanel = new MenuSelectionJPanel(controller, map,this, panel, startBox, arrivalBox);
        verticalRootJPanel.add(rootJPanel);
        verticalRootJPanel.add(selectionJPanel);
        add(verticalRootJPanel);
        add(map);
    }

    public static MapJPanel getInstanceMap() {
        return map;
    }

    public void updateRootPanel(JComponent rootJPanel) {
        this.rootJPanel.removeAll();
        this.rootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}
