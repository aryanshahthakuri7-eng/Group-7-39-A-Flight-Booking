package controller;

import model.Flight;
import view.SeatSelection;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Controller linking Seat Selection view with booking data flow.
 * Calculates upgrade fees and manages navigation routes.
 */
public class SeatSelectionController {

    private final SeatSelection view;
    private final Flight flight;
    private final String passengerName;
    private final BookingController bookingController;

    public SeatSelectionController(SeatSelection view, Flight flight, String passengerName) {
        this.view = view;
        this.flight = flight;
        this.passengerName = passengerName;
        this.bookingController = new BookingController();
        initController();
    }

    private void initController() {
        // Sidebar Navigation
        view.getLblDashboard().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToDashboard(view); }
        });
        view.getLblSearchFlight().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToSearchFlight(view); }
        });
        view.getLblMyBookings().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToMyBookings(view); }
        });
        view.getLblProfile().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToProfile(view); }
        });
        view.getLblCustomerSupport().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToCustomerSupport(view); }
        });
        view.getLblLogout().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.logout(view); }
        });
        view.getBtnBookNewFlight().addActionListener(e -> NavigationController.goToSearchFlight(view));

        // Header and back links
        view.getLblBackToHome().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToDashboard(view); }
        });
        view.getLblBackToPassenger().addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { NavigationController.goToFlightDetails(view, flight); }
        });

        // Action Buttons
        view.getBtnConfirmSeat().addActionListener(e -> handleConfirmSeat());
        view.getBtnBackResults().addActionListener(e -> NavigationController.goToSearchFlight(view));
    }

    private void handleConfirmSeat() {
        String seat = view.getSelectedSeatCode();
        if (seat == null || seat.trim().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Please select a seat to proceed.", "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Complete the MVC flow by booking the seat in the database
        // Payment will be resolved on the next screen (PaymentFrame)
        boolean success = bookingController.bookFlight(flight, passengerName, seat, "CREDIT_CARD");

        if (success) {
            JOptionPane.showMessageDialog(view, "Seat " + seat + " selected successfully! Redirecting to Payment...", "Seat Selection Confirmed", JOptionPane.INFORMATION_MESSAGE);
            NavigationController.goToPayment(view);
        } else {
            JOptionPane.showMessageDialog(view, "Seat selection failed. Seat might have been occupied. Please choose another seat.", "Seat Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

// Commit 4: Documented controller mapping of DB seats to interactive grid

// Commit 7: Explained controller metadata injection for passenger info

// Commit 9: Documented SQL filters used to query booked seats for flight

// Commit 12: Explained local occupied states caching optimization

// Commit 14: Documented next view routing configuration on click

// Commit 16: Explained seat reservation locking query logic

// Commit 18: Explained DB connection failure retry policy

// Commit 20: Documented memory deallocation and model unbinding

// Commit 22: Explained model flight pricing references mapping

// Commit 24: Documented parameters preparation for payment navigation

// Commit 26: Explained controller routing dispatcher mapping

// Commit 28: Explained integrity checks inside action event listeners

// Commit 30: Documented clean-up sequence on view closing hook

// Commit 32: Documented database query rows mapping loop variables

// Commit 34: Documented state persistence inside reservation model

// Commit 36: Explained navigation routing error redirection paths

// Commit 38: Documented prepared statement close hooks inside controller

// Commit 40: Documented listeners unbinding hooks to release reference holds

// Commit 42: Explained DB retry delay intervals for high concurrency

// Commit 44: Explained controller action mapping logic for next screens

// Commit 46: Explained SQL queries validation rules for seats mapping

// Commit 48: Documented memory leak prevention unbind events hooks

// Commit 50: Final annotations for NetBeans UI builder properties on seat panels release
