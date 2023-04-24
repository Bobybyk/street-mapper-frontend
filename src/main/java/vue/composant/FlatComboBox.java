package vue.composant;

import vue.utils.BuilderJComposant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlatComboBox extends JComboBox<String> {
    public FlatComboBox(String[] items) {
        super(items);
        setEditable(true);
        setEditor(new ComboBoxCustom());
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

    private class ComboBoxCustom extends BasicComboBoxEditor {
        private final JTextField field = new JTextField();

        public ComboBoxCustom() {
            super();
            field.setBorder(null);
            field.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (field.getText().length() > 0) field.selectAll();
                }
            });
            setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    return new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                }
            });
        }

        @Override
        public Component getEditorComponent() {
            return this.field;
        }

        @Override
        public Object getItem() {
            return this.field.getText();
        }

        @Override
        public void setItem(Object item) {
            String text = (item != null) ? item.toString() : "";
            this.field.setText(text);
            this.field.setSelectionStart(0);
            this.field.setSelectionEnd(text.length());
        }

    }
}