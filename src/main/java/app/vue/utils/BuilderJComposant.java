package app.vue.utils;

import app.vue.composant.FlatJButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;

public class BuilderJComposant {

    public static JButton createJButton(String buttonName){
        return new FlatJButton(buttonName);
    }

    public static Font lemontRegularFont(float value){
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/font/LEMONMILK-Regular.otf")).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, 30);
    }

    public static Font lemonLightFont(float value){
        try {
            final Font font = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/font/LEMONMILK-Light.otf")).deriveFont(value);
            return font;
        } catch (FontFormatException | IOException e) {
            System.out.println("Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF,  Font.BOLD, 30);
    }
}
