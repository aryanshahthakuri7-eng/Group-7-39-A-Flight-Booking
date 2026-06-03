package controller;

import javax.swing.JFrame;
import view.dashboard;
import view.searchflight;
import view.mybookings;
import view.profie;
import view.customersupport;
import view.livechat;

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

    /**
     * Opens the Live Chat window without closing the current frame,
     * so the user can return to Customer Support after chatting.
     */
    public static void goToLiveChat() {
        java.awt.EventQueue.invokeLater(() -> {
            new livechat().setVisible(true);
        });
    }

    public static void logout(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        System.out.println("User logged out successfully.");
        System.exit(0);
    }
}