package vue.composant;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class FlatJButtonRound extends JButton {



    public FlatJButtonRound(int radius, String text){
        super(text);
        setBorder(new RoundBorder(radius));
    }

    public static class RoundBorder implements Border {
        private final int radius;

        public RoundBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D roundRect = new RoundRectangle2D.Float(x, y, width - 1f, height - 1f, radius, radius);
            g2d.setColor(c.getForeground());
            g2d.draw(roundRect);
            g2d.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            int borderWidth = Math.max(radius / 2, 1);
            return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
        }

        @Override
        public boolean isBorderOpaque() {
            return true;
        }
    }

}
