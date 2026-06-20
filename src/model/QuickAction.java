/*
 * Model class representing a Quick Action item on the Dashboard.
 * Used with Array and ArrayList in DashboardController.
 */
package model;

/**
 * QuickAction Model - holds data for a single quick action button.
 * (e.g., Search Flights, My Bookings, Profile Settings, Logout)
 * 
 * @author Lenovo LOQ
 */
public class QuickAction {
    
    // Field variables containing properties for Quick Action layout items on Dashboard
    private String icon;       // Emoji icon (e.g., "🔍", "💼", "⚙️", "🚪")
    private String label;      // Display label (e.g., "Search Flights")
    private String actionKey;  // Action identifier for the controller (e.g., "SEARCH", "BOOKINGS", "PROFILE", "LOGOUT")
    
    // Default constructor
    public QuickAction() {
    }
    
    // Parameterized constructor
    public QuickAction(String icon, String label, String actionKey) {
        this.icon = icon;
        this.label = label;
        this.actionKey = actionKey;
    }
    
    // ======================== GETTERS ========================
    
    public String getIcon() {
        return icon;
    }
    
    public String getLabel() {
        return label;
    }
    
    public String getActionKey() {
        return actionKey;
    }
    
    // ======================== SETTERS ========================
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }
    
    /**
     * Returns the formatted button text with icon for display.
     * Example: "🔍   Search Flights"
     */
    public String getFormattedButtonText() {
        return icon + "   " + label;
    }
    
    @Override
    public String toString() {
        return "QuickAction{" + "label=" + label + ", actionKey=" + actionKey + '}';
    }
}
