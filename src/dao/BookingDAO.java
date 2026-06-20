package dao;

import database.DatabaseConnection;
import model.Booking;
import model.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for bookings table.
 * Manages database operations on bookings using PreparedStatements and Transactions.
 */
public class BookingDAO {

    /**
     * Inserts a new booking and associated payment, and decrements flight seat availability.
     * Performed inside a database transaction to ensure data integrity.
     */
    public boolean addBookingWithPayment(Booking booking, Payment payment) {
        Connection conn = null;
        PreparedStatement checkFlightStmt = null;
        PreparedStatement insertBookingStmt = null;
        PreparedStatement insertPaymentStmt = null;
        PreparedStatement updateSeatsStmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start Transaction

            // 1. Verify seat availability on the flight
            String checkFlightSql = "SELECT available_seats FROM flights WHERE flight_id = ? FOR UPDATE";
            checkFlightStmt = conn.prepareStatement(checkFlightSql);
            checkFlightStmt.setInt(1, booking.getFlightId());
            rs = checkFlightStmt.executeQuery();

            if (!rs.next() || rs.getInt("available_seats") <= 0) {
                conn.rollback();
                return false; // No seats available
            }

            // 2. Generate unique booking ID if not set
            if (booking.getBookingId() == null || booking.getBookingId().trim().isEmpty()) {
                booking.setBookingId(generateUniqueBookingId(conn));
            }
            payment.setBookingId(booking.getBookingId());

            // 3. Save booking details in database
            String insertBookingSql = "INSERT INTO bookings (booking_id, user_id, flight_id, passenger_name, seat_number, amount, booking_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertBookingStmt = conn.prepareStatement(insertBookingSql);
            insertBookingStmt.setString(1, booking.getBookingId());
            insertBookingStmt.setInt(2, booking.getUserId());
            insertBookingStmt.setInt(3, booking.getFlightId());
            insertBookingStmt.setString(4, booking.getPassengerName());
            insertBookingStmt.setString(5, booking.getSeatNumber());
            insertBookingStmt.setDouble(6, booking.getAmount());
            insertBookingStmt.setString(7, booking.getStatus());
            insertBookingStmt.executeUpdate();

            // 4. Save payment details in database
            String insertPaymentSql = "INSERT INTO payments (booking_id, amount, payment_method) VALUES (?, ?, ?)";
            insertPaymentStmt = conn.prepareStatement(insertPaymentSql);
            insertPaymentStmt.setString(1, payment.getBookingId());
            insertPaymentStmt.setDouble(2, payment.getAmount());
            insertPaymentStmt.setString(3, payment.getPaymentMethod());
            insertPaymentStmt.executeUpdate();

            // 5. Reduce available seats automatically
            String updateSeatsSql = "UPDATE flights SET available_seats = available_seats - 1 WHERE flight_id = ?";
            updateSeatsStmt = conn.prepareStatement(updateSeatsSql);
            updateSeatsStmt.setInt(1, booking.getFlightId());
            updateSeatsStmt.executeUpdate();

            conn.commit(); // Commit Transaction
            return true;

        } catch (SQLException e) {
            System.err.println("Transaction failed, rolling back booking insertion: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            closeResources(rs, checkFlightStmt, null);
            closeResources(null, insertBookingStmt, null);
            closeResources(null, insertPaymentStmt, null);
            closeResources(null, updateSeatsStmt, null);
            try {
                if (conn != null) conn.setAutoCommit(true); // Reset connection state
            } catch (SQLException e) {
                System.err.println("Failed to reset autocommit: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Cancels a booking, updates booking_status to 'CANCELLED', and restores seat availability.
     * Performed inside a database transaction.
     */
    public boolean cancelBooking(String bookingId) {
        Connection conn = null;
        PreparedStatement checkBookingStmt = null;
        PreparedStatement updateBookingStmt = null;
        PreparedStatement updateSeatsStmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false); // Start Transaction

            // 1. Get booking details to verify it is confirmed and find flight_id
            String checkBookingSql = "SELECT flight_id, booking_status FROM bookings WHERE booking_id = ? FOR UPDATE";
            checkBookingStmt = conn.prepareStatement(checkBookingSql);
            checkBookingStmt.setString(1, bookingId);
            rs = checkBookingStmt.executeQuery();

            if (!rs.next() || !"CONFIRMED".equalsIgnoreCase(rs.getString("booking_status"))) {
                conn.rollback();
                return false; // Booking not found or already cancelled
            }

            int flightId = rs.getInt("flight_id");

            // 2. Set booking status to CANCELLED
            String updateBookingSql = "UPDATE bookings SET booking_status = 'CANCELLED' WHERE booking_id = ?";
            updateBookingStmt = conn.prepareStatement(updateBookingSql);
            updateBookingStmt.setString(1, bookingId);
            updateBookingStmt.executeUpdate();

            // 3. Increase available seats automatically
            String updateSeatsSql = "UPDATE flights SET available_seats = available_seats + 1 WHERE flight_id = ?";
            updateSeatsStmt = conn.prepareStatement(updateSeatsSql);
            updateSeatsStmt.setInt(1, flightId);
            updateSeatsStmt.executeUpdate();

            conn.commit(); // Commit Transaction
            return true;

        } catch (SQLException e) {
            System.err.println("Transaction failed, rolling back booking cancellation: " + e.getMessage());
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            closeResources(rs, checkBookingStmt, null);
            closeResources(null, updateBookingStmt, null);
            closeResources(null, updateSeatsStmt, null);
            try {
                if (conn != null) conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Failed to reset autocommit: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * Updates the status of a specific booking in the database.
     */
    public boolean updateBookingStatus(String bookingId, String newStatus) {
        String sql = "UPDATE bookings SET booking_status = ? WHERE booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newStatus);
            pstmt.setString(2, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating booking status: " + e.getMessage());
        }
        return false;
    }

    /**
     * Gets all bookings from the database.
     */
    public ArrayList<Booking> getAllBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, f.flight_code, f.source, f.destination, f.departure_date, f.departure_time " +
                     "FROM bookings b JOIN flights f ON b.flight_id = f.flight_id " +
                     "ORDER BY b.booking_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(mapRowToBooking(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all bookings: " + e.getMessage());
        }
        return bookings;
    }

    /**
     * Gets bookings belonging to a specific user.
     */
    public ArrayList<Booking> getBookingsByUserId(int userId) {
        ArrayList<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.*, f.flight_code, f.source, f.destination, f.departure_date, f.departure_time " +
                     "FROM bookings b JOIN flights f ON b.flight_id = f.flight_id " +
                     "WHERE b.user_id = ? " +
                     "ORDER BY b.booking_date DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    bookings.add(mapRowToBooking(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching bookings for user: " + e.getMessage());
        }
        return bookings;
    }

    /**
     * Gets a single booking by its unique ID.
     */
    public Booking getBookingById(String bookingId) {
        String sql = "SELECT b.*, f.flight_code, f.source, f.destination, f.departure_date, f.departure_time " +
                     "FROM bookings b JOIN flights f ON b.flight_id = f.flight_id " +
                     "WHERE b.booking_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, bookingId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToBooking(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting booking by ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Calculates the total amount spent by a specific user on all bookings.
     */
    public double getTotalAmountSpentByUserId(int userId) {
        String sql = "SELECT SUM(amount) AS total_spent FROM bookings WHERE user_id = ? AND booking_status = 'CONFIRMED'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("total_spent");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error calculating user total spent: " + e.getMessage());
        }
        return 0.0;
    }

    /**
     * Gets count of bookings for a user by status (e.g. 'CONFIRMED', 'CANCELLED').
     */
    public int getBookingCountByStatusAndUserId(String status, int userId) {
        String sql = "SELECT COUNT(*) AS cnt FROM bookings WHERE user_id = ? AND booking_status = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, status);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cnt");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error counting user bookings by status: " + e.getMessage());
        }
        return 0;
    }

    // ======================== HELPER METHODS ========================

    private String generateUniqueBookingId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(booking_id, 3) AS UNSIGNED)) AS max_id FROM bookings";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            int nextNum = 1;
            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                if (maxId > 0) {
                    nextNum = maxId + 1;
                }
            }
            return String.format("BK%03d", nextNum);
        }
    }

    private Booking mapRowToBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getString("booking_id"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setFlightId(rs.getInt("flight_id"));
        booking.setPassengerName(rs.getString("passenger_name"));
        booking.setSeatNumber(rs.getString("seat_number"));
        booking.setAmount(rs.getDouble("amount"));
        booking.setStatus(rs.getString("booking_status"));
        booking.setBookingDate(rs.getTimestamp("booking_date"));
        
        // Joined columns
        booking.setFlightCode(rs.getString("flight_code"));
        String source = rs.getString("source");
        String destination = rs.getString("destination");
        booking.setFromCity(source);
        booking.setToCity(destination);
        booking.setRoute(source + " → " + destination);
        booking.setDepartureDate(rs.getString("departure_date"));
        booking.setDepartureTime(rs.getString("departure_time"));
        booking.setArrivalTime(""); // Blank by default, or loaded from database if added
        
        return booking;
    }

    private void closeResources(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            System.err.println("Error closing Statement: " + e.getMessage());
        }
    }
}
