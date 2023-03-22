package app.vue.composant;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import java.awt.*;

public class FlatSearchBar extends JTextField {


    public FlatSearchBar(){
        setFont(BuilderJComposant.lemonLightFont(30f));
        setSelectedTextColor(Color.green);
    }


}
