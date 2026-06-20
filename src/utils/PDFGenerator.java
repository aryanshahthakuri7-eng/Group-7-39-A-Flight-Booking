package utils;

import model.Booking;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Utility to generate and download flight tickets.
 * Writes a structured, readable ticket file representing a boarding pass.
 */
public class PDFGenerator {

    /**
     * Generates a ticket file at the specified location.
     */
    public static boolean generateTicketFile(Booking booking, File destinationFile) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(destinationFile))) {
            writer.println("--------------------------------------------------");
            writer.println("                  YATRA AIR SEWA                  ");
            writer.println("                 BOARDING PASS                    ");
            writer.println("--------------------------------------------------");
            writer.println(String.format(" Booking ID     : %s", booking.getBookingId()));
            writer.println(String.format(" Passenger Name : %s", booking.getPassengerName()));
            writer.println(String.format(" Flight Code    : %s", booking.getFlightCode()));
            writer.println(String.format(" Route          : %s", booking.getRoute()));
            writer.println(String.format(" Departure Date : %s", booking.getDepartureDate()));
            writer.println(String.format(" Departure Time : %s", booking.getDepartureTime()));
            writer.println(String.format(" Seat Number    : %s", booking.getSeatNumber()));
            writer.println(String.format(" Ticket Price   : %s", booking.getFormattedAmount()));
            writer.println(String.format(" Status         : %s", booking.getStatus()));
            writer.println("--------------------------------------------------");
            writer.println("                 [QR CODE PLACEHOLDER]            ");
            writer.println("                                                  ");
            writer.println("         Thank you for flying with us!            ");
            writer.println("--------------------------------------------------");
            return true;
        } catch (IOException e) {
            System.err.println("Error saving ticket: " + e.getMessage());
            return false;
        }
    }
}
