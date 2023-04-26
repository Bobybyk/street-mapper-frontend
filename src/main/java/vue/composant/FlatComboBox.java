package vue.composant;

import utils.Observable;
import utils.Observer;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class FlatComboBox extends JComboBox<String> implements Observable {

    private final List<Observer> listObserver = new ArrayList<>();

    public FlatComboBox() {
        super();
        setEditable(true);
        setEditor(new ComboBoxCustom());
        setPrototypeDisplayValue("XXXXXXXXXXXXXX");
        setFont(BuilderJComposant.lemontRegularFont(20));
        JTextField textField = (JTextField) getEditor().getEditorComponent();
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isPopupVisible() || getItemCount() == 0) hidePopup();
                else showPopup();
            }
        });
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
    }

    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listObserver) {
            observer.update(this);
        }
    }

    private class ComboBoxCustom extends BasicComboBoxEditor {
        private final JTextField field = new JTextField();

        public ComboBoxCustom() {
            super();
            field.setBorder(null);
            field.setPreferredSize(new Dimension(10, 10));
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
            this.field.setSelectionEnd(text.length());
        }
    }
    public String getText(){
        return super.getToolTipText();
    }
}