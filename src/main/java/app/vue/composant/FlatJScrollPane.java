package app.vue.composant;

import app.vue.utils.BuilderJComposant;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.basic.BasicScrollPaneUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Locale;

public class FlatJScrollPane extends JScrollPane {


    public FlatJScrollPane(JPanel panel){
        super(panel);
        setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        setViewportBorder(BorderFactory.createEmptyBorder());
        getHorizontalScrollBar().setUI(new FlatJScrollBarUI());
        getVerticalScrollBar().setUI(new FlatJScrollBarUI());

    }

    private class FlatJScrollBarUI extends BasicScrollBarUI {

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            Graphics2D graphics2D = (Graphics2D) g.create();
            graphics2D.setColor(Color.GREEN);
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


    }

}
