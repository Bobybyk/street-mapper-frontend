package vue.utils;

import vue.composant.FlatJButton;
import vue.composant.FlatJTextField;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Fabrique statique BuilderJComposant permet
 * de creer des composants graphiques
 *
 */
public class BuilderJComposant {

    public static FlatJButton createJButton(String buttonName){
        return new FlatJButton(buttonName);
    }
    public static FlatJButton createJButton(String buttonName, String path){
        return new FlatJButton(buttonName, path);
    }

    public static Font lemontRegularFont(float value){
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Props.fontPathNormal)).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, 30);
    }

    public static Font lemonLightFont(float value){
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File(Props.fontPathLight)).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, 30);
    }

    public static JPanel createPanelBoxLayoutVertical() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static JPanel createPanelBoxLayoutHorizontal() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    public static FlatJTextField createFlatJTextField(String placeHolder) {
        return new FlatJTextField(placeHolder);
    }

    public static JList<String> createJList(DefaultListModel list) {
        final JList jList = new JList(list);
       // jList.setFont(lemonLightFont(18));
        jList.setVisibleRowCount(1);
        jList.setSelectedIndex(0);
        return jList;
    }

    public static DefaultListModel fillJListString(String ... values){
        DefaultListModel<String> model = new DefaultListModel<>();
        for (String v: values) model.addElement(v);
        return model;
    }
}
