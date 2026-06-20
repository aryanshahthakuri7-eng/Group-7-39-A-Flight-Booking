package dao;

import database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

/**
 * LocationDAO handles airport locations data queries from MySQL locations table.
 * Used to populate flight search drop-down lists.
 */
public class LocationDAO {

    /**
     * Retrieves all locations from the database formatted as "City (CODE)".
     */
    public ArrayList<String> getAllLocations() {
        ArrayList<String> locations = new ArrayList<>();
        String sql = "SELECT city_name, city_code FROM locations ORDER BY city_name ASC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String cityName = rs.getString("city_name");
                String cityCode = rs.getString("city_code");
                locations.add(cityName + " (" + cityCode + ")");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching locations from DB: " + e.getMessage());
        }
        return locations;
    }

    /**
     * Returns an array of locations for the 'From' airport dropdown.
     */
    public String[] getFromLocationsArray() {
        ArrayList<String> locations = getAllLocations();
        String[] result = new String[locations.size() + 1];
        result[0] = "📍 Select Region"; // Placeholder
        for (int i = 0; i < locations.size(); i++) {
            result[i + 1] = locations.get(i);
        }
        return result;
    }

    /**
     * Returns an array of locations for the 'To' airport dropdown.
     */
    public String[] getToLocationsArray() {
        ArrayList<String> locations = getAllLocations();
        String[] result = new String[locations.size() + 1];
        result[0] = "📍 Select Destination"; // Placeholder
        for (int i = 0; i < locations.size(); i++) {
            result[i + 1] = locations.get(i);
        }
        return result;
    }

    /**
     * Inserts a location into database.
     */
    public boolean addLocation(String cityName, String airportName, String cityCode) {
        String sql = "INSERT INTO locations (city_name, airport_name, city_code) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cityName);
            pstmt.setString(2, airportName);
            pstmt.setString(3, cityCode);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error adding location: " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes a location by city code.
     */
    public boolean deleteLocation(String cityCode) {
        String sql = "DELETE FROM locations WHERE city_code = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cityCode);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting location: " + e.getMessage());
        }
        return false;
    }
}
