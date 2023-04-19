package vue.composant;

import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlatComboBox extends JComboBox<String> {
    public FlatComboBox(String[] items) {
        super(items);
        setEditable(true);
        setFont(BuilderJComposant.lemontRegularFont(20));
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPopupVisible()) hidePopup();
                else showPopup();
            }
        });
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                showPopup();
            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                hidePopup();
            }
        });
    }
}