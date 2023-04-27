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

    public FlatComboBox(String placeHolder) {
        setEditable(true);
        setEditor(new ComboBoxCustom(placeHolder));
        setFont(BuilderJComposant.lemontRegularFont(20));
    }

    public FlatJTextField getTextField(){
        return (FlatJTextField) getEditor().getEditorComponent();
    }

    @Override
    public void addObserver(Observer observer) {
        listObserver.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : listObserver) observer.update(this);
    }

    private class ComboBoxCustom extends BasicComboBoxEditor {

        private final FlatJTextField field;

        public ComboBoxCustom(String placeHolder) {
            super();
            field = BuilderJComposant.createFlatJTextField(placeHolder,  new Dimension(150, 70));
            field.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent e) {
                    if (field.getText().length() > 0) field.selectAll();
                }
            });
            field.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (isPopupVisible() || getItemCount() == 0) hidePopup();
                    else showPopup();
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
            repaint();
            revalidate();
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
}