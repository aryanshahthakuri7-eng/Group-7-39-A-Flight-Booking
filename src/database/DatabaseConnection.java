package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Reusable DatabaseConnection utility to manage JDBC connections to MySQL.
 * Automatically initializes database and runs tables setup if offline or empty.
 */
public class DatabaseConnection {

    private static Connection connection = null;
    private static final String[] PASSWORDS = {"rahul1234", "1234", "", "root", "password"};
    private static String successfulPassword = null;

    /**
     * Retrieves or establishes a connection to MySQL database.
     * Tries common passwords and handles automatic initialization.
     */
    public static synchronized Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Ensure connector jar is present.", e);
        }

        // 1. Try cached password first if already found
        if (successfulPassword != null) {
            String url = "jdbc:mysql://localhost:3306/yatraair?useSSL=false&allowPublicKeyRetrieval=true";
            try {
                connection = DriverManager.getConnection(url, "root", successfulPassword);
                return connection;
            } catch (SQLException e) {
                successfulPassword = null; // Reset if failed
            }
        }

        // 2. Loop through common passwords to find a connection
        SQLException lastException = null;
        for (String pwd : PASSWORDS) {
            // First check connection to MySQL server itself (to ensure it exists and create database if missing)
            String serverUrl = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
            try (Connection conn = DriverManager.getConnection(serverUrl, "root", pwd)) {
                successfulPassword = pwd;
                
                // Perform database setup
                initializeDatabase(conn);
                
                // Reconnect to the newly created/existing database
                String dbUrl = "jdbc:mysql://localhost:3306/yatraair?useSSL=false&allowPublicKeyRetrieval=true";
                connection = DriverManager.getConnection(dbUrl, "root", pwd);
                return connection;
            } catch (SQLException e) {
                lastException = e;
            }
        }

        throw new SQLException("Failed to connect to local MySQL server at localhost:3306. " +
                               "Please ensure MySQL is running and root password is one of (1234, empty, root, password).", lastException);
    }

    /**
     * Creates database and creates tables with seed data if they do not exist.
     */
    private static void initializeDatabase(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            // Create database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS yatraair;");
            stmt.executeUpdate("USE yatraair;");

            // Create users table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                    "user_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "full_name VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) UNIQUE NOT NULL," +
                    "password VARCHAR(255) NOT NULL," +
                    "phone VARCHAR(20)," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                    ");");

            // Create flights table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS flights (" +
                    "flight_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "flight_code VARCHAR(10) NOT NULL UNIQUE," +
                    "source VARCHAR(50) NOT NULL," +
                    "destination VARCHAR(50) NOT NULL," +
                    "departure_date VARCHAR(20) NOT NULL," +
                    "departure_time VARCHAR(10) NOT NULL," +
                    "available_seats INT NOT NULL," +
                    "price DOUBLE NOT NULL," +
                    "status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE'" +
                    ");");

            // Create bookings table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bookings (" +
                    "booking_id VARCHAR(10) PRIMARY KEY," +
                    "user_id INT NOT NULL," +
                    "flight_id INT NOT NULL," +
                    "passenger_name VARCHAR(100) NOT NULL," +
                    "seat_number VARCHAR(5) NOT NULL," +
                    "booking_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "amount DOUBLE NOT NULL," +
                    "booking_status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED'," +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE," +
                    "FOREIGN KEY (flight_id) REFERENCES flights(flight_id) ON DELETE CASCADE" +
                    ");");

            // Create payments table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS payments (" +
                    "payment_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "booking_id VARCHAR(10) NOT NULL," +
                    "amount DOUBLE NOT NULL," +
                    "payment_method VARCHAR(50) NOT NULL," +
                    "payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE" +
                    ");");

            // Create locations table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS locations (" +
                    "location_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "city_name VARCHAR(50) NOT NULL," +
                    "airport_name VARCHAR(100) NOT NULL," +
                    "city_code VARCHAR(5) NOT NULL UNIQUE" +
                    ");");

            // Create support_tickets table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS support_tickets (" +
                    "ticket_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "user_id INT NOT NULL," +
                    "subject VARCHAR(255) NOT NULL," +
                    "message TEXT NOT NULL," +
                    "status VARCHAR(50) DEFAULT 'OPEN'," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" +
                    ");");

            // Create support_messages table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS support_messages (" +
                    "message_id INT AUTO_INCREMENT PRIMARY KEY," +
                    "ticket_id INT NULL," +
                    "user_id INT NOT NULL," +
                    "message TEXT NOT NULL," +
                    "sender VARCHAR(50) NOT NULL," +
                    "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                    "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" +
                    ");");


            // Check if seeding is needed (e.g. if users is empty)
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users;")) {
                if (rs.next() && rs.getInt(1) == 0) {
                    // Seed users (using 'User Name' to match mockup)
                    stmt.executeUpdate("INSERT INTO users (user_id, full_name, email, password, phone) VALUES " +
                            "(1, 'User Name', 'user@yatraair.com', 'password123', '+977 9812345678')," +
                            "(2, 'Aryan Shah', 'aryan.shah@gmail.com', 'password123', '+977 9876543210');");

                    // Seed locations
                    stmt.executeUpdate("INSERT INTO locations (location_id, city_name, airport_name, city_code) VALUES " +
                            "(1, 'Kathmandu', 'Tribhuvan International Airport', 'KTM')," +
                            "(2, 'Pokhara', 'Pokhara International Airport', 'PKR')," +
                            "(3, 'Biratnagar', 'Biratnagar Airport', 'BIR')," +
                            "(4, 'Bhairahawa', 'Gautam Buddha International Airport', 'BWA')," +
                            "(5, 'Nepalgunj', 'Nepalgunj Airport', 'KEP')," +
                            "(6, 'Lukla', 'Tenzing-Hillary Airport', 'LUA');");

                    // Seed flights
                    stmt.executeUpdate("INSERT INTO flights (flight_id, flight_code, source, destination, departure_date, departure_time, available_seats, price, status) VALUES " +
                            "(1, 'YS101', 'Kathmandu (KTM)', 'Pokhara (PKR)', '28 APR 2026', '10:00AM', 10, 5000.0, 'ACTIVE')," +
                            "(2, 'YS205', 'Pokhara (PKR)', 'Kathmandu (KTM)', '15 MAY 2026', '02:30PM', 15, 5500.0, 'ACTIVE')," +
                            "(3, 'YS310', 'Kathmandu (KTM)', 'Biratnagar (BIR)', '10 JUN 2026', '08:00AM', 20, 4500.0, 'ACTIVE')," +
                            "(4, 'YS420', 'Kathmandu (KTM)', 'Lukla (LUA)', '20 JUN 2026', '06:00AM', 5, 8000.0, 'ACTIVE')," +
                            "(5, 'YS502', 'Kathmandu (KTM)', 'Bhairahawa (BWA)', '25 JUN 2026', '11:15AM', 18, 4800.0, 'ACTIVE');");

                    // Seed bookings (Amount: 5000 + 7500 = 12500 total spent)
                    stmt.executeUpdate("INSERT INTO bookings (booking_id, user_id, flight_id, passenger_name, seat_number, booking_date, amount, booking_status) VALUES " +
                            "('BK001', 1, 1, 'User Name', 'A2', '2026-04-01 10:00:00', 5000.0, 'CONFIRMED')," +
                            "('BK002', 1, 2, 'User Name', 'B5', '2026-04-10 14:30:00', 7500.0, 'CONFIRMED')," +
                            "('BK003', 1, 3, 'User Name', 'C1', '2026-03-01 08:00:00', 4500.0, 'CANCELLED');");

                    // Seed payments
                    stmt.executeUpdate("INSERT INTO payments (payment_id, booking_id, amount, payment_method, payment_date) VALUES " +
                            "(1, 'BK001', 5000.0, 'E-SEWA', '2026-04-01 10:05:00')," +
                            "(2, 'BK002', 7500.0, 'KHALTI', '2026-04-10 14:35:00');");
                } else {
                    // Check if old seed data needs migration to match mockup
                    try (ResultSet rsCheck = stmt.executeQuery("SELECT COUNT(*) FROM users WHERE user_id = 1 AND full_name = 'Gaurav Chandra';")) {
                        if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                            System.out.println("Migrating database seed data to match mockup...");
                            stmt.executeUpdate("UPDATE users SET full_name = 'User Name', email = 'user@yatraair.com' WHERE user_id = 1;");
                            stmt.executeUpdate("UPDATE bookings SET passenger_name = 'User Name' WHERE user_id = 1;");
                            stmt.executeUpdate("UPDATE bookings SET amount = 7500.0 WHERE booking_id = 'BK002';");
                            stmt.executeUpdate("DELETE FROM payments WHERE booking_id = 'BK004';");
                            stmt.executeUpdate("DELETE FROM bookings WHERE booking_id = 'BK004';");
                        }
                    }
                }
            }
        }
    }
}
