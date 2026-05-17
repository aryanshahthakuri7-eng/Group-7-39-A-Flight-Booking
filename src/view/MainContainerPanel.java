package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import javax.swing.JPanel;

/**
 * Custom panel drawing the centered container with rounded corners, top header bar,
 * status bar backgrounds, and a subtle shadow wrapper.
 */
public class MainContainerPanel extends JPanel {
    
    public MainContainerPanel() {
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
        
        // 1. Draw soft drop shadow border around the card
        g2.setColor(new Color(0, 0, 0, 15));
        for (int i = 1; i <= 5; i++) {
            g2.drawRoundRect(i, i, w - 2 * i, h - 2 * i, 16, 16);
        }
        
        // 2. Fill white background for the main card container
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, w - 1, h - 1, 16, 16);
        
        // 3. Fill top header background (dark navy, height 60px)
        g2.setColor(new Color(8, 22, 42)); // #08162A
        g2.fillRoundRect(0, 0, w - 1, 60, 16, 16);
        g2.fillRect(0, 50, w - 1, 10); // Flatten the bottom edge of the top header
        
        // 4. Fill bottom status bar background (light grey, height 25px)
        g2.setColor(new Color(243, 244, 246)); // #F3F4F6
        g2.fillRoundRect(0, h - 25, w - 1, 25, 16, 16);
        g2.fillRect(0, h - 25, w - 1, 10); // Flatten the top edge of the status bar
        
        // 5. Draw light grey outline border around the entire container
        g2.setColor(new Color(229, 231, 235)); // #E5E7EB
        g2.setStroke(new BasicStroke(1.0f));
        g2.drawRoundRect(0, 0, w - 1, h - 1, 16, 16);
        
        g2.dispose();
    }
}
