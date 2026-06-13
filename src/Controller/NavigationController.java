package controller;

import javax.swing.JFrame;
import model.Booking;
import model.SessionManager;
import view.dashboard;
import view.searchflight;
import view.mybookings;
import view.profie;
import view.customersupport;
import view.login;
import view.checkin;
import view.ticketwindow;
import view.signup;
import view.PaymentFrame;

/**
 * Controller to handle application navigation flow.
 */
public class NavigationController {

    public static void goToLogin(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new login().setVisible(true);
        });
    }

    public static void goToSignUp(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new signup().setVisible(true);
        });
    }

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

    public static void goToCheckIn(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new checkin().setVisible(true);
        });
    }

    /**
     * Opens the Ticket Receipt screen as a standalone popup window.
     */
    public static void openTicketWindow(Booking booking) {
        java.awt.EventQueue.invokeLater(() -> {
            new ticketwindow(booking).setVisible(true);
        });
    }

    public static void logout(JFrame currentFrame) {
        SessionManager.clearSession();
        goToLogin(currentFrame);
    }

    public static void goToPayment(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new PaymentFrame().setVisible(true);
        });
    }
}
