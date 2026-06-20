package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;
import javax.swing.JPanel;

/**
 * A custom JPanel that draws a light-blue notice banner with rounded corners,
 * a thick blue left border line, and a circular info vector icon on the left.
 */
public class NoticePanel extends JPanel {
    
    public NoticePanel() {
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
        
        // 1. Draw light blue rounded background fill (#E6F0FF or #EBF3FE)
        g2.setColor(new Color(235, 243, 254)); // #EBF3FE
        g2.fillRoundRect(0, 0, w - 1, h - 1, 8, 8);
        
        // 2. Draw thick blue vertical indicator bar on the left edge (4px width)
        g2.setColor(new Color(15, 61, 117)); // Dark blue #0F3D75
        g2.fillRoundRect(0, 0, 4, h, 2, 2);
        
        // 3. Draw vector circular info icon (i inside circle)
        int ix = 15;
        int iy = (h - 14) / 2;
        g2.setStroke(new java.awt.BasicStroke(1.5f));
        g2.drawOval(ix, iy, 14, 14);
        g2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 10));
        g2.drawString("i", ix + 5, iy + 10);
        
        g2.dispose();
    }
}
