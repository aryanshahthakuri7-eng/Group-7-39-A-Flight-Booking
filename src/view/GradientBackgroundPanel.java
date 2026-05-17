package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import javax.swing.JPanel;

/**
 * A panel that paints a gradient from #0D1B31 to #060D1A, matching the mockup outer background.
 */
public class GradientBackgroundPanel extends JPanel {
    
    public GradientBackgroundPanel() {
        super();
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        GradientPaint gp = new GradientPaint(
            0, 0, new Color(13, 27, 49), // #0D1B31
            0, getHeight(), new Color(6, 13, 26) // #060D1A
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
    }
}
