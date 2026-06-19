package controller;

import dao.BookingDAO;
import model.Booking;
import utils.PDFGenerator;
import java.io.File;

/**
 * Controller to handle retrieving, downloading, and printing ticket actions.
 */
public class TicketController {

    private final BookingDAO bookingDAO;

    public TicketController() {
        this.bookingDAO = new BookingDAO();
    }

    /**
     * Retrieves ticket details by Booking ID.
     */
    public Booking getTicketDetails(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) {
            return null;
        }
        return bookingDAO.getBookingById(bookingId);
    }

    /**
     * Downloads ticket to a file using PDFGenerator.
     */
    public boolean downloadTicket(Booking booking, File destinationFile) {
        if (booking == null || destinationFile == null) {
            return false;
        }
        return PDFGenerator.generateTicketFile(booking, destinationFile);
    }

    /**
     * Prints ticket (simulated or real).
     */
    public boolean printTicket(Booking booking) {
        if (booking == null) {
            return false;
        }
        
        // Print simulation: print to standard output (system logs) or return success
        System.out.println("----------------------------------------------");
        System.out.println("PRINTING TICKET:");
        System.out.println("YATRA AIR SEWA BOARDING TICKET");
        System.out.println("Booking ID: " + booking.getBookingId());
        System.out.println("Passenger: " + booking.getPassengerName());
        System.out.println("Flight: " + booking.getFlightCode());
        System.out.println("Route: " + booking.getRoute());
        System.out.println("Seat: " + booking.getSeatNumber());
        System.out.println("Date/Time: " + booking.getDepartureDate() + " @ " + booking.getDepartureTime());
        System.out.println("Price: " + booking.getFormattedAmount());
        System.out.println("Status: " + booking.getStatus());
        System.out.println("----------------------------------------------");
        return true;
    }
}
