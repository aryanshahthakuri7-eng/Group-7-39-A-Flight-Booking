/*
 * Model class representing a Dashboard Statistics Card.
 * Used with Array and ArrayList in DashboardController.
 */
package model;

/**
 * StatCard Model - holds data for a single statistics card on the dashboard.
 * (e.g., Upcoming Trips: 2, Cancelled Trips: 1, Total Spent: NPR 12,500, Loyalty Points: 120)
 * 
 * @author Lenovo LOQ
 */
public class StatCard {
    
    // Field variables containing stats details that are dynamically drawn on stats dashboard grid cards
    private String icon;       // Emoji icon (e.g., "▲", "⟲", "💳", "✨")
    private String value;      // Display value (e.g., "2", "NPR 12,500", "120")
    private String label;      // Title label (e.g., "UPCOMING TRIPS")
    private String subtitle;   // Subtitle text (e.g., "View your next booking")
    
    // Default constructor
    public StatCard() {
    }
    
    // Parameterized constructor
    public StatCard(String icon, String value, String label, String subtitle) {
        this.icon = icon;
        this.value = value;
        this.label = label;
        this.subtitle = subtitle;
    }
    
    // ======================== GETTERS ========================
    
    public String getIcon() {
        return icon;
    }
    
    public String getValue() {
        return value;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    // ======================== SETTERS ========================
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    @Override
    public String toString() {
        return "StatCard{" + "label=" + label + ", value=" + value + '}';
    }
}
