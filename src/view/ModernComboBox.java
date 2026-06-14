package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 * A styled combo box with rounded corners, a clean chevron dropdown arrow,
 * white background, and focused highlight states matching standard fields.
 */
public class ModernComboBox<E> extends JComboBox<E> {
    
    private boolean isFocused = false;
    private final Color borderColor = new Color(209, 213, 219); // #D1D5DB
    private final Color focusBorderColor = new Color(15, 61, 117); // Dark navy/blue
    
    public ModernComboBox() {
        super();
        setOpaque(false);
        setBackground(Color.WHITE);
        
        // Listen to focus changes
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                isFocused = true;
                repaint();
            }
            @Override
            public void focusLost(FocusEvent e) {
                isFocused = false;
                repaint();
            }
        });
        
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(0, 0, getWidth(), getHeight());
                        
                        // Draw clean minimal chevron arrow "v"
                        g2.setColor(new Color(119, 119, 119));
                        g2.setStroke(new java.awt.BasicStroke(1.5f));
                        int ax = getWidth() / 2 - 4;
                        int ay = getHeight() / 2 - 2;
                        g2.drawLine(ax, ay, ax + 4, ay + 4);
                        g2.drawLine(ax + 4, ay + 4, ax + 8, ay);
                        g2.dispose();
                    }
                };
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setPreferredSize(new Dimension(30, 30));
                return button;
            }
            
            @Override
            public void paintCurrentValueBackground(Graphics g, java.awt.Rectangle bounds, boolean hasFocus) {
                g.setColor(Color.WHITE);
                g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        });
        
        // padding on left
        setBorder(BorderFactory.createEmptyBorder(0, 12, 0, 0));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fill white rounded background
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        g2.dispose();
        
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (isFocused) {
            g2.setColor(focusBorderColor);
            g2.setStroke(new java.awt.BasicStroke(1.5f));
        } else {
            g2.setColor(borderColor);
            g2.setStroke(new java.awt.BasicStroke(1.0f));
        }
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        g2.dispose();
    }
}
