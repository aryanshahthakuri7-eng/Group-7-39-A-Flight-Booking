package controller;

import dao.BookingDAO;
import model.Booking;
import model.Payment;
import model.Flight;
import model.SessionManager;
import model.User;
import java.util.ArrayList;

/**
 * Controller to manage booking and cancellation processes.
 */
public class BookingController {

    private final BookingDAO bookingDAO;

    public BookingController() {
        this.bookingDAO = new BookingDAO();
    }

    /**
     * Books a flight for a passenger.
     * Decreases seats and inserts payment dynamically.
     */
    public boolean bookFlight(Flight flight, String passengerName, String seatNumber, String paymentMethod) {
        if (flight == null || passengerName == null || passengerName.trim().isEmpty() ||
            seatNumber == null || seatNumber.trim().isEmpty() || paymentMethod == null) {
            return false;
        }

        User user = SessionManager.getCurrentUser();

        // 1. Construct Booking
        Booking booking = new Booking();
        booking.setUserId(user.getUserId());
        booking.setFlightId(flight.getFlightId());
        booking.setPassengerName(passengerName.trim());
        booking.setSeatNumber(seatNumber.trim().toUpperCase());
        booking.setAmount(flight.getPrice());
        booking.setStatus("CONFIRMED");

        // 2. Construct Payment
        Payment payment = new Payment();
        payment.setAmount(flight.getPrice());
        payment.setPaymentMethod(paymentMethod);

        // 3. Save to database via DAO transaction
        return bookingDAO.addBookingWithPayment(booking, payment);
    }

    /**
     * Cancels a booking and restores seats.
     */
    public boolean cancelBooking(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            return false;
        }
        return bookingDAO.cancelBooking(bookingId);
    }

    /**
     * Gets all bookings of the currently logged-in user.
     */
    public ArrayList<Booking> getLoggedInUserBookings() {
        User user = SessionManager.getCurrentUser();
        return bookingDAO.getBookingsByUserId(user.getUserId());
    }
}
