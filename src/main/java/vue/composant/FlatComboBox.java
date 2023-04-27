package vue.composant;

import app.server.data.SuggestionStations;
import controller.Controller;
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
import java.util.Timer;
import java.util.TimerTask;

public class FlatComboBox extends JComboBox<String> implements Observable {

    private final List<Observer> listObserver = new ArrayList<>();
    private FlatJTextField field;

    public FlatComboBox(String placeHolder) {
        setEditable(true);
        setEditor(new ComboBoxCustom(placeHolder));
        Dimension d = new Dimension(235, 100);
        setPreferredSize(d);
        setMinimumSize(d);
        setMaximumSize(d);
        setFont(BuilderJComposant.lemontRegularFont(18));

        setRenderer(new CustomListCellRenderer());

        addActionListener(e -> {
            if(e.getModifiers() != 0){
                String selectedItem = (String) getSelectedItem();
                field.setText(selectedItem);
                field.revalidate();
            }
        });
    }

    public void requestInitComboBox(Controller controler, SuggestionStations.SuggestionKind depart) {
        getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String word = ((JTextField) getEditor().getEditorComponent()).getText();
                char c = e.getKeyChar();
                if (Character.isLetterOrDigit(c)) {
                    showPopup();
                    java.util.Timer te = new Timer();
                    te.purge();
                    te.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            controler.sendRequestSearch(word, depart);
                            cancel();
                        }
                    }, 0, 400);
                }
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

    static class CustomListCellRenderer extends JLabel implements ListCellRenderer<Object> {
        public CustomListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.toString());
            setFont(BuilderJComposant.lemontRegularFont(14));
            if (isSelected) {
                setBackground(new Color(127, 177, 50));
                setForeground(Color.BLACK);
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
}