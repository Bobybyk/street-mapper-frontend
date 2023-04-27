package vue.composant;

import utils.Observable;
import utils.Observer;
import vue.utils.BuilderJComposant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.BasicComboBoxUI;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class FlatComboBox extends JComboBox<String> implements Observable {

    private final List<Observer> listObserver = new ArrayList<>();
    private FlatJTextField field;

    public FlatComboBox(String placeHolder) {
        setEditable(true);
        setEditor(new ComboBoxCustom(placeHolder));
        setFont(BuilderJComposant.lemontRegularFont(20));
        Dimension d = new Dimension(235, 150);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);

       // setRenderer(new CustomListCellRenderer());

        addActionListener(e -> {
            if(e.getModifiers() != 0){
                String selectedItem = (String) getSelectedItem();
                field.setText(selectedItem);
                field.revalidate();
            }
        });
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
            return field;
        }

        @Override
        public Object getItem() {
            return field.getText();
        }

    }
}