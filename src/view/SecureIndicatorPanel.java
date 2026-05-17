package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * A custom JPanel that draws a secure checkout notice box:
 * - Light-blue rounded card background (#F0F6FF)
 * - 1px light blue-grey border outline (#D1E4FF)
 * - A clean vector check-shield security icon on the left
 */
public class SecureIndicatorPanel extends JPanel {
    
    public SecureIndicatorPanel() {
        super();
        setOpaque(false);
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        int w = getWidth();
        int h = getHeight();
        
        // 1. Fill light blue background (#F0F6FF)
        g2.setColor(new Color(240, 246, 255));
        g2.fillRoundRect(0, 0, w - 1, h - 1, 8, 8);
        
        // 2. Draw border outline (#D1E4FF)
        g2.setColor(new Color(209, 228, 255));
        g2.setStroke(new java.awt.BasicStroke(1.0f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 8, 8);
        
        // 3. Draw check-shield vector icon on the left
        int ix = 18;
        int iy = 18;
        g2.setColor(new Color(15, 61, 117)); // Dark blue #0F3D75
        g2.setStroke(new java.awt.BasicStroke(1.8f));
        
        // Draw shield outline
        int[] xs = {ix, ix + 7, ix + 14, ix + 14, ix + 7, ix};
        int[] ys = {iy + 2, iy, iy + 2, iy + 9, iy + 14, iy + 9};
        g2.drawPolygon(xs, ys, 6);
        
        // Draw checkmark inside shield
        g2.setStroke(new java.awt.BasicStroke(1.5f));
        g2.drawLine(ix + 4, iy + 7, ix + 6, iy + 9);
        g2.drawLine(ix + 6, iy + 9, ix + 10, iy + 5);
        
        g2.dispose();
    }
}
