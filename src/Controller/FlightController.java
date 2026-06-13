package controller;

import dao.FlightDAO;
import model.Flight;
import java.util.List;

/**
 * Controller to manage flights lookup and search operations.
 * Implements sanitization of location options and fetches matching flight lists from DAO.
 */
public class FlightController {

    private final FlightDAO flightDAO;

    public FlightController() {
        this.flightDAO = new FlightDAO();
    }

    /**
     * Searches flights in database matching source, destination, and optional date.
     */
    public List<Flight> searchFlights(String source, String destination, String date) {
        // Strip code annotations like 'Kathmandu (KTM)' to search names directly
        String cleanSource = cleanLocationString(source);
        String cleanDestination = cleanLocationString(destination);
        return flightDAO.searchFlights(cleanSource, cleanDestination, date);
    }

    /**
     * Retrieves all flights.
     */
    public List<Flight> getAllFlights() {
        return flightDAO.getAll();
    }

    /**
     * Helper to clean locations from combo boxes.
     * e.g., "Kathmandu (KTM)" -> "Kathmandu"
     */
    private String cleanLocationString(String loc) {
        if (loc == null || loc.trim().isEmpty() || loc.contains("Select")) {
            return "";
        }
        int index = loc.indexOf('(');
        if (index != -1) {
            return loc.substring(0, index).trim();
        }
        return loc.trim();
    }
}
