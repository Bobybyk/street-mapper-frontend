package vue.panel;

import app.App;
import controller.Controller;
import vue.composant.FlatComboBox;

import javax.swing.*;

/**
 * RootJPanel est la jpanel principal ou les elements vont être
 * changé lors d'un événement de clique
 */

public class RootJPanel extends JPanel {

    private final JPanel attRootJPanel;

    public RootJPanel(Controller controller, ResearchPanel panel, FlatComboBox startBox, FlatComboBox arrivalBox) {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        attRootJPanel = new JPanel();
        JPanel verticalRootJPanel = new JPanel();
        verticalRootJPanel.setLayout(new BoxLayout(verticalRootJPanel, BoxLayout.Y_AXIS));
        attRootJPanel.add(new SearchTrajetJPanel(controller, panel, startBox, arrivalBox));
        MenuSelectionJPanel selectionJPanel = new MenuSelectionJPanel(controller, this, panel, startBox, arrivalBox);
        verticalRootJPanel.add(attRootJPanel);
        verticalRootJPanel.add(selectionJPanel);
        add(verticalRootJPanel);
        add(App.getInstanceMap());
    }

    public void updateRootPanel(JComponent rootJPanel) {
        this.attRootJPanel.removeAll();
        this.attRootJPanel.add(rootJPanel);
        repaint();
        updateUI();
    }

}
