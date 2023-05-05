package vue.utils;

import app.App;
import console.Debug;
import console.DebugList;
import vue.composant.FlatJButton;
import vue.composant.FlatJRadioButton;
import vue.composant.FlatJTextField;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * Fabrique statique BuilderJComposant permet
 * de creer des composants graphiques
 */
public class BuilderJComposant {

    private BuilderJComposant() {}

    public static FlatJButton createJButton(String buttonName) {
        return new FlatJButton(buttonName);
    }

    public static FlatJButton createJButton(String buttonName, String path) {
        return new FlatJButton(buttonName, path);
    }

    public static Font lemontRegularFont(float value) {
        try {
            InputStream stream = App.class.getResourceAsStream(Props.FONT_PATH_NORMAL);
            if (stream == null) throw new IOException();
            return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(value);
        } catch (FontFormatException | IOException e) {
            Debug.print(DebugList.ERROR, "[ERROR/BuilderJComposant] Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF, Font.BOLD, 30);
    }

    public static Font lemonLightFont(float value) {
        try {
            InputStream stream = App.class.getResourceAsStream(Props.FONT_PATH_LIGHT);
            if (stream == null) throw new IOException();
            return Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(value);
        } catch (FontFormatException | IOException e) {
            Debug.print(DebugList.ERROR, "[ERROR/BuilderJComposant] Erreur chargement des polices d'écritures");
        }
        return new Font(Font.SANS_SERIF, Font.BOLD, 30);
    }

    public static JPanel createPanelBoxLayoutVertical() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }
    public static JPanel createPanelBoxLayoutVertical(String name) {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static JPanel createPanelBoxLayoutHorizontal() {
        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    public static JPanel createPanelBoxLayoutHorizontal(String name) {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(name));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    public static JPanel createPanelBoxLayoutHorizontalRounded(Dimension dimension) {
        Border roundedBorder = new Border() {
            private static final int RADIUS = 12;

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(RADIUS + 1, RADIUS + 1, RADIUS + 2, RADIUS);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.BLACK);
                g.drawRoundRect(x + 12, y + 12, width - 24, height - 26, RADIUS, RADIUS);
            }
        };

        final JPanel panel = new JPanel();
        panel.setPreferredSize(dimension);
        panel.setMaximumSize(dimension);
        panel.setMinimumSize(dimension);

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(roundedBorder);
        panel.setOpaque(false);
        return panel;
    }

    public static FlatJTextField createFlatJTextField(String placeHolder) {
        return new FlatJTextField(placeHolder, new Dimension(200, 100));
    }

    public static FlatJTextField createFlatJTextField(String placeHolder, Dimension d) {
        return new FlatJTextField(placeHolder, d);
    }

    public static Component createJLabelStyle(String s, float v, Color color) {
        final JLabel label = new JLabel(s);
        label.setFont(lemontRegularFont(v));
        label.setForeground(color);
        return label;
    }

    public static FlatJRadioButton createJRadioButton(String text){
        return new FlatJRadioButton(text);
    }

    public static JPanel createPanelGridPanelRounded(int rows, int cols) {
        Border roundedBorder = new Border() {
            private static final int RADIUS = 12;

            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(RADIUS + 1, RADIUS + 1, RADIUS + 2, RADIUS);
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                g.setColor(Color.BLACK);
                g.drawRoundRect(x+12, y+12, width - 24, height - 26, RADIUS, RADIUS);
            }
        };

        JPanel panel = new JPanel();
        GridLayout layout = new GridLayout(rows, cols);
        layout.setHgap(1);
        layout.setVgap(1);
        panel.setLayout(layout);
        panel.setBorder(roundedBorder);
        panel.setOpaque(false);
        return panel;
    }
}
