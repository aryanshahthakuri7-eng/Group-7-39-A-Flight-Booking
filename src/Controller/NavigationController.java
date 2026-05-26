package controller;

import javax.swing.JFrame;
import view.dashboard;
import view.searchflight;
import view.mybookings;
import view.profie;
import view.customersupport;

/**
 * Controller class to handle all navigation logic.
 * Decouples the business logic/navigation from the view layer.
 */
public class NavigationController {

    public static void goToDashboard(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new dashboard().setVisible(true);
        });
    }

    public static void goToSearchFlight(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new searchflight().setVisible(true);
        });
    }

    public static void goToMyBookings(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new mybookings().setVisible(true);
        });
    }

    public static void goToProfile(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new profie().setVisible(true);
        });
    }

    public static void goToCustomerSupport(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new customersupport().setVisible(true);
        });
    }

    public static void logout(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        // In a real application, this would go back to the login screen.
        // Since no auth logic is requested, we will just terminate or print to console.
        System.out.println("User logged out successfully.");
        System.exit(0);
    }
}
