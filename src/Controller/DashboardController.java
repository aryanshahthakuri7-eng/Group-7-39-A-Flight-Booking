package controller;

import dao.BookingDAO;
import dao.LocationDAO;
import java.util.ArrayList;
import model.Booking;
import model.SessionManager;
import model.StatCard;
import model.User;

/**
 * DashboardController handles loading all metrics, upcoming trips, and search
 * data for the dashboard dynamically from the database.
 */
public class DashboardController {

    private final BookingDAO bookingDAO;
    private final LocationDAO locationDAO;

    private ArrayList<Booking> userBookings;
    private ArrayList<Booking> upcomingBookings;
    private ArrayList<Booking> cancelledBookings;
    private ArrayList<StatCard> statCards;

    private String[] fromLocations;
    private String[] toLocations;
    private String[] passengerOptions;

    public DashboardController() {
        this.bookingDAO = new BookingDAO();
        this.locationDAO = new LocationDAO();
        
        this.userBookings = new ArrayList<>();
        this.upcomingBookings = new ArrayList<>();
        this.cancelledBookings = new ArrayList<>();
        this.statCards = new ArrayList<>();
        
        refreshData();
    }

    /**
     * Refreshes all metrics, stats cards, and booking details from MySQL.
     */
    public final void refreshData() {
        User currentUser = SessionManager.getCurrentUser();
        int userId = currentUser.getUserId();

        // 1. Fetch bookings from DB
        this.userBookings = bookingDAO.getBookingsByUserId(userId);
        
        // 2. Filter bookings into lists
        this.upcomingBookings.clear();
        this.cancelledBookings.clear();
        for (Booking booking : userBookings) {
            if ("CONFIRMED".equalsIgnoreCase(booking.getStatus())) {
                upcomingBookings.add(booking);
            } else if ("CANCELLED".equalsIgnoreCase(booking.getStatus())) {
                cancelledBookings.add(booking);
            }
        }

        // Sort upcoming bookings chronologically by departure date (nearest trip first)
        // Parses dates using English locale to guarantee correct ordering format comparison
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale.ENGLISH);
        upcomingBookings.sort((b1, b2) -> {
            try {
                java.util.Date d1 = sdf.parse(b1.getDepartureDate());
                java.util.Date d2 = sdf.parse(b2.getDepartureDate());
                return d1.compareTo(d2);
            } catch (Exception e) {
                return b1.getDepartureDate().compareTo(b2.getDepartureDate());
            }
        });

        // 3. Populate statistics cards dynamically from database.
        // Each card represents a key metric fetched via the BookingDAO for the currently logged-in user session.
        this.statCards.clear();
        
        // Card 0: Upcoming Trips (Active confirmed travels)
        int upcomingCount = bookingDAO.getBookingCountByStatusAndUserId("CONFIRMED", userId);
        this.statCards.add(new StatCard("▲", String.valueOf(upcomingCount), "UPCOMING TRIPS", "View your next booking"));

        // Card 1: Cancelled Trips
        int cancelledCount = bookingDAO.getBookingCountByStatusAndUserId("CANCELLED", userId);
        this.statCards.add(new StatCard("⟲", String.valueOf(cancelledCount), "CANCELLED TRIPS", "View cancelled bookings"));

        // Card 2: Total Spent (only confirmed bookings)
        double totalSpent = bookingDAO.getTotalAmountSpentByUserId(userId);
        this.statCards.add(new StatCard("💳", "NPR " + String.format("%,.0f", totalSpent), "TOTAL SPENT", "View your transactions"));

        // Card 3: Loyalty Points (60 points per confirmed booking to yield 120 for 2 bookings)
        int loyaltyPoints = upcomingCount * 60;
        this.statCards.add(new StatCard("✨", String.valueOf(loyaltyPoints), "LOYALTY POINTS", "Visit reward center"));

        // 4. Load drop-down locations from MySQL.
        // Retrieves the list of available airports/cities from the locations table to populate search fields.
        this.fromLocations = locationDAO.getFromLocationsArray();
        this.toLocations = locationDAO.getToLocationsArray();

        // 5. Hardcoded passengers count options (static ui config).
        // Provides default passenger selection dropdown options for local flights.
        this.passengerOptions = new String[] {
            "1 Passenger",
            "2 Passengers",
            "3 Passengers",
            "4 Passengers",
            "5 Passengers"
        };
    }

    // ======================== GETTERS (Used by the View) ========================

    public String[] getFromLocations() {
        return fromLocations;
    }

    public String[] getToLocations() {
        return toLocations;
    }

    public String[] getPassengerOptions() {
        return passengerOptions;
    }

    public ArrayList<Booking> getAllBookings() {
        return userBookings;
    }

    public ArrayList<Booking> getUpcomingBookings() {
        return upcomingBookings;
    }

    public ArrayList<Booking> getCancelledBookings() {
        return cancelledBookings;
    }

    public ArrayList<StatCard> getStatCards() {
        return statCards;
    }

    public Booking getFirstUpcomingBooking() {
        if (!upcomingBookings.isEmpty()) {
            return upcomingBookings.get(0);
        }
        return null;
    }

    public int getTotalBookingsCount() {
        return userBookings.size();
    }

    public double getTotalAmountSpent() {
        User currentUser = SessionManager.getCurrentUser();
        return bookingDAO.getTotalAmountSpentByUserId(currentUser.getUserId());
    }

    public int getLoyaltyPoints() {
        User currentUser = SessionManager.getCurrentUser();
        int upcomingCount = bookingDAO.getBookingCountByStatusAndUserId("CONFIRMED", currentUser.getUserId());
        return upcomingCount * 60;
    }

    public boolean hasUpcomingBookings() {
        return !upcomingBookings.isEmpty();
    }

    public String getWelcomeMessage() {
        User user = SessionManager.getCurrentUser();
        String name = user.getFullName();
        if ("User Name".equals(name)) {
            return "Welcome User,";
        }
        if (name != null && name.contains(" ")) {
            name = name.split(" ")[0];
        }
        return "Welcome " + name + ",";
    }

    public String getWelcomeSubtitle() {
        return "Review your flight schedules and upcoming travels.";
    }

    public String getSystemStatus() {
        if (isDatabaseConnected()) {
            return "SYSTEM STATUS: OPERATIONAL";
        } else {
            return "SYSTEM STATUS: OFFLINE";
        }
    }
    
    public boolean isDatabaseConnected() {
        try (java.sql.Connection conn = database.DatabaseConnection.getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
    
    // Quick Actions collection compatibility (dummy list, since replaced by Recent Tickets in view)
    public ArrayList<model.QuickAction> getQuickActions() {
        ArrayList<model.QuickAction> quickActions = new ArrayList<>();
        quickActions.add(new model.QuickAction("🔍", "Search Flights", "SEARCH"));
        quickActions.add(new model.QuickAction("💼", "My Bookings", "BOOKINGS"));
        quickActions.add(new model.QuickAction("⚙️", "Profile Settings", "PROFILE"));
        quickActions.add(new model.QuickAction("🚪", "Logout", "LOGOUT"));
        return quickActions;
    }
}
