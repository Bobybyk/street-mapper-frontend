package vue.composant;

import vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

/**
 * FlatJTextField est une class JTextField
 * avec des parametres graphiques déjà fait pour facilité
 * l'implementation graphique
 *
 */
public class FlatJTextField extends JTextField {


    public FlatJTextField(String placeHolder, Dimension d){
        setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        setFont(BuilderJComposant.lemontRegularFont(20));
        setText(placeHolder);
        setPreferredSize(d);
        setMaximumSize(d);
        setMinimumSize(d);
        setForeground(Color.GRAY);
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getText().equals(placeHolder)){
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(getText().isEmpty()){
                    setText(placeHolder);
                    setForeground(Color.gray);
                }
            }
        });
    }

}
