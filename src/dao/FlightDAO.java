package dao;

import database.DatabaseConnection;
import model.Flight;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for flights table.
 * Handles database queries for inserting, updating, and searching flight records.
 */
public class FlightDAO {

    public boolean insert(Flight flight) {
        String sql = "INSERT INTO flights (flight_code, source, destination, departure_date, departure_time, available_seats, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, flight.getFlightCode());
            pstmt.setString(2, flight.getSource());
            pstmt.setString(3, flight.getDestination());
            pstmt.setString(4, flight.getDepartureDate());
            pstmt.setString(5, flight.getDepartureTime());
            pstmt.setInt(6, flight.getAvailableSeats());
            pstmt.setDouble(7, flight.getPrice());
            pstmt.setString(8, flight.getStatus());
            
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        flight.setFlightId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error inserting flight: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Flight flight) {
        String sql = "UPDATE flights SET flight_code = ?, source = ?, destination = ?, departure_date = ?, departure_time = ?, available_seats = ?, price = ?, status = ? WHERE flight_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, flight.getFlightCode());
            pstmt.setString(2, flight.getSource());
            pstmt.setString(3, flight.getDestination());
            pstmt.setString(4, flight.getDepartureDate());
            pstmt.setString(5, flight.getDepartureTime());
            pstmt.setInt(6, flight.getAvailableSeats());
            pstmt.setDouble(7, flight.getPrice());
            pstmt.setString(8, flight.getStatus());
            pstmt.setInt(9, flight.getFlightId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating flight: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(int flightId) {
        String sql = "DELETE FROM flights WHERE flight_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, flightId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting flight: " + e.getMessage());
        }
        return false;
    }

    public Flight getById(int flightId) {
        String sql = "SELECT * FROM flights WHERE flight_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, flightId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToFlight(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting flight by id: " + e.getMessage());
        }
        return null;
    }

    public List<Flight> getAll() {
        List<Flight> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights ORDER BY departure_date ASC, departure_time ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                flights.add(mapRowToFlight(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all flights: " + e.getMessage());
        }
        return flights;
    }

    public List<Flight> searchFlights(String source, String destination, String date) {
        List<Flight> flights = new ArrayList<>();
        // Search matches source and destination (matching name or code like 'Kathmandu (KTM)')
        String sql = "SELECT * FROM flights WHERE source LIKE ? AND destination LIKE ? AND status = 'ACTIVE' ORDER BY departure_time ASC";
        if (date != null && !date.trim().isEmpty() && !date.equalsIgnoreCase("DD/MM/YYYY")) {
            sql = "SELECT * FROM flights WHERE source LIKE ? AND destination LIKE ? AND departure_date = ? AND status = 'ACTIVE' ORDER BY departure_time ASC";
        }
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + source + "%");
            pstmt.setString(2, "%" + destination + "%");
            if (date != null && !date.trim().isEmpty() && !date.equalsIgnoreCase("DD/MM/YYYY")) {
                pstmt.setString(3, date);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    flights.add(mapRowToFlight(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching flights: " + e.getMessage());
        }
        return flights;
    }

    private Flight mapRowToFlight(ResultSet rs) throws SQLException {
        Flight flight = new Flight();
        flight.setFlightId(rs.getInt("flight_id"));
        flight.setFlightCode(rs.getString("flight_code"));
        flight.setSource(rs.getString("source"));
        flight.setDestination(rs.getNString("destination"));
        flight.setDepartureDate(rs.getString("departure_date"));
        flight.setDepartureTime(rs.getString("departure_time"));
        flight.setAvailableSeats(rs.getInt("available_seats"));
        flight.setPrice(rs.getDouble("price"));
        flight.setStatus(rs.getString("status"));
        return flight;
    }
}
