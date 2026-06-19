package controller;

import javax.swing.JFrame;
import model.Booking;
import model.SessionManager;
import view.Dashboard;
import view.SearchFlight;
import view.MyBookings;
import view.Profile;
import view.CustomerSupport;
import view.Login;
import view.CheckIn;
import view.TicketWindow;
import view.SignUp;
import view.PaymentFrame;
import view.FlightDetails;

/**
 * Controller to handle application navigation flow.
 */
public class NavigationController {

    public static void goToLogin(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    public static void goToSignUp(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new SignUp().setVisible(true);
        });
    }

    public static void goToDashboard(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Dashboard().setVisible(true);
        });
    }

    public static void goToSearchFlight(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new SearchFlight().setVisible(true);
        });
    }

    public static void goToMyBookings(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MyBookings().setVisible(true);
        });
    }

    public static void goToProfile(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Profile().setVisible(true);
        });
    }

    public static void goToCustomerSupport(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new CustomerSupport().setVisible(true);
        });
    }

    public static void goToCheckIn(JFrame currentFrame) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new CheckIn().setVisible(true);
        });
    }

    /**
     * Opens the Ticket Receipt screen as a standalone popup window.
     */
    public static void openTicketWindow(Booking booking) {
        java.awt.EventQueue.invokeLater(() -> {
            new TicketWindow(booking).setVisible(true);
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

    public static void goToFlightDetails(JFrame currentFrame, model.Flight flight) {
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FlightDetails().setVisible(true);
        });
    }
}
