package dao;

import database.DatabaseConnection;
import model.PasswordRecovery;
import java.sql.*;

/**
 * Data Access Object for managing password recovery requests and OTP codes.
 */
public class PasswordRecoveryDAO {

    public PasswordRecoveryDAO() {
        createTableIfNotExists();
    }

    /**
     * Creates the password_recoveries table if it does not already exist.
     */
    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS password_recoveries (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "email VARCHAR(100) NOT NULL," +
                     "otp VARCHAR(10) NOT NULL," +
                     "expiry_time TIMESTAMP NOT NULL," +
                     "is_verified BOOLEAN NOT NULL DEFAULT FALSE" +
                     ");";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error creating password_recoveries table: " + e.getMessage());
        }
    }

    /**
     * Inserts a new password recovery record.
     */
    public boolean insert(PasswordRecovery recovery) {
        String sql = "INSERT INTO password_recoveries (email, otp, expiry_time, is_verified) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, recovery.getEmail());
            pstmt.setString(2, recovery.getOtp());
            pstmt.setTimestamp(3, recovery.getExpiryTime());
            pstmt.setBoolean(4, recovery.isVerified());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        recovery.setRecoveryId(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error inserting password recovery record: " + e.getMessage());
        }
        return false;
    }

    /**
     * Deletes recovery records for a specific email.
     */
    public boolean delete(String email) {
        String sql = "DELETE FROM password_recoveries WHERE email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting recovery records: " + e.getMessage());
        }
        return false;
    }

    /**
     * Updates the verification status of a recovery record.
     */
    public boolean update(PasswordRecovery recovery) {
        String sql = "UPDATE password_recoveries SET is_verified = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, recovery.isVerified());
            pstmt.setInt(2, recovery.getRecoveryId());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating verification status: " + e.getMessage());
        }
        return false;
    }

    /**
     * Retrieves the latest recovery record for a given email.
     */
    public PasswordRecovery getLatestByEmail(String email) {
        String sql = "SELECT * FROM password_recoveries WHERE email = ? ORDER BY id DESC LIMIT 1";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    PasswordRecovery recovery = new PasswordRecovery();
                    recovery.setRecoveryId(rs.getInt("id"));
                    recovery.setEmail(rs.getString("email"));
                    recovery.setOtp(rs.getString("otp"));
                    recovery.setExpiryTime(rs.getTimestamp("expiry_time"));
                    recovery.setVerified(rs.getBoolean("is_verified"));
                    return recovery;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting latest recovery record: " + e.getMessage());
        }
        return null;
    }
}
