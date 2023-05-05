package vue.composant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

/**
 * FlatJScrollPane est une class JScrollPane
 * avec des parametres graphiques déjà fait pour facilité
 * l'implementation graphique
 *
 */

public class FlatJScrollPane extends JScrollPane {


    public FlatJScrollPane(JPanel panel){
        super(panel);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setViewportBorder(BorderFactory.createEmptyBorder());
        getHorizontalScrollBar().setUI(new FlatJScrollBarUI());
        getVerticalScrollBar().setUI(new FlatJScrollBarUI());

    }

    private static class FlatJScrollBarUI extends BasicScrollBarUI {


        @Override
        protected Rectangle getThumbBounds() {
            return new Rectangle(0, 0, 30, 30);
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.translate(thumbRect.x, thumbRect.y);
            graphics2D.fill(new Ellipse2D.Double(0, 0, thumbRect.width, thumbRect.height));
            graphics2D.dispose();
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.white);
            int size = 35;
            if (scrollbar.getOrientation() == JSlider.HORIZONTAL) {
                int x = 0;
                int y = (trackRect.height - size) / 2;
                g2.fill(new RoundRectangle2D.Double(trackRect.x + x, trackRect.y + y, trackRect.width, size, size, size));
            } else {
                int x = (trackRect.width - size) / 2;
                int y = 0;
                g2.fill(new RoundRectangle2D.Double(trackRect.x + x, trackRect.y + y, size, trackRect.height, size, size));
            }
            g2.dispose();
        }


        @Override
        protected void paintDecreaseHighlight(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            int size = 20;
            g2.setColor(Color.blue);
            g2.fill(new RoundRectangle2D.Double(trackRect.x, trackRect.y, trackRect.width, size, size, size));
            g2.dispose();
        }

        @Override
        protected void paintIncreaseHighlight(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            int size = 20;
            g2.setColor(Color.blue);
            g2.fill(new RoundRectangle2D.Double(trackRect.x, trackRect.y, trackRect.width, size, size, size));
            g2.dispose();
        }
    }

}
