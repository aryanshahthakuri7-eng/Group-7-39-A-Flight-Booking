package group.pkg7.pkg39.a.flight.booking;

import view.Login;

/**
 * Main application entry point for Yatra Air Sewa.
 * Starts the application on the Login Screen.
 */
public class Group739AFlightBooking {

    public static void main(String[] args) {
        // Test database connection on startup
        try {
            System.out.println("Checking database connection...");
            try (java.sql.Connection conn = database.DatabaseConnection.getConnection()) {
                if (conn != null && !conn.isClosed()) {
                    System.out.println("Connection successful!");
                } else {
                    System.out.println("Unsuccessful connection.");
                }
            }
        } catch (Exception e) {
            System.out.println("Unsuccessful connection.");
            System.err.println("Error details: " + e.getMessage());
        }

        java.awt.EventQueue.invokeLater(() -> {
            Login loginView = new Login();
            new controller.LoginController(loginView);
            loginView.setVisible(true);
        });
    }
}
