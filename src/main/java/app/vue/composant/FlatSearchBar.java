package app.vue.composant;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class FlatSearchBar extends JTextField {

    /**
     * FlatSearchBar est une class JTextField
     * avec des parametres graphiques déjà fait pour facilité
     * l'implementation graphique
     */


    public FlatSearchBar(){
        setFont(BuilderJComposant.lemonLightFont(30f));
        setSelectedTextColor(Color.green);
    }


}
