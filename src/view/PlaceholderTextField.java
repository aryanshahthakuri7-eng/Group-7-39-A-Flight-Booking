package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.BorderFactory;

/**
 * A modern JTextField with rounded corners, custom margins (padding),
 * placeholder text support, right-aligned icon display, and focus state border transitions.
 */
public class PlaceholderTextField extends JTextField {
    
    private String placeholder = "";
    private boolean isFocused = false;
    private Icon rightIcon = null;
    
    private final Color borderColor = new Color(209, 213, 219); // #D1D5DB
    private final Color focusBorderColor = new Color(15, 61, 117); // Dark navy/blue
    private final Color placeholderColor = new Color(156, 163, 175); // #9CA3AF
    
    public PlaceholderTextField() {
        super();
        init();
    }
    
    public PlaceholderTextField(String placeholder) {
        super();
        this.placeholder = placeholder;
        init();
    }
    
    private void init() {
        setOpaque(false);
        // Default margin padding inside the text field
        setMargin(new Insets(0, 12, 0, 12));
        
        // Listen to focus changes to repaint the border color
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
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }
    
    public Icon getRightIcon() {
        return rightIcon;
    }
    
    public void setRightIcon(Icon icon) {
        this.rightIcon = icon;
        int rightPadding = (icon != null) ? icon.getIconWidth() + 16 : 12;
        setMargin(new Insets(0, 12, 0, rightPadding));
        repaint();
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Fill white rounded background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);
        
        g2.dispose();
        
        super.paintComponent(g);
        
        // Draw placeholder text if empty
        if (getText().isEmpty() && placeholder != null && !placeholder.isEmpty()) {
            Graphics2D gPlaceholder = (Graphics2D) g.create();
            gPlaceholder.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gPlaceholder.setColor(placeholderColor);
            gPlaceholder.setFont(getFont().deriveFont(java.awt.Font.PLAIN));
            
            Insets insets = getInsets();
            int tx = insets.left + 2;
            int ty = gPlaceholder.getFontMetrics().getAscent() + (getHeight() - gPlaceholder.getFontMetrics().getHeight()) / 2;
            gPlaceholder.drawString(placeholder, tx, ty);
            gPlaceholder.dispose();
        }
        
        // Draw right icon if present
        if (rightIcon != null) {
            int ix = getWidth() - rightIcon.getIconWidth() - 12;
            int iy = (getHeight() - rightIcon.getIconHeight()) / 2;
            rightIcon.paintIcon(this, g, ix, iy);
        }
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Paint rounded border depending on focus state
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
