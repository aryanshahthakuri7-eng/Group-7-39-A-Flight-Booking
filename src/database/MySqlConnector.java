package database;

import java.sql.*;

public class MySqlConnector implements Db {

    private final String url;
    private final String username = "root";
    private final String password = "rahul1234";

    public MySqlConnector() {
        this.url = "jdbc:mysql://localhost:3306/yatraair?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true";
        initializeDatabase();
    }

    private void initializeDatabase() {
        // Load MySQL driver first to ensure it's available
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password);
                 Statement stmt = conn.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS users (" +
                             "id INT AUTO_INCREMENT PRIMARY KEY, " +
                             "fullname VARCHAR(100) NOT NULL, " +
                             "email VARCHAR(100) NOT NULL UNIQUE, " +
                             "phone VARCHAR(20) NOT NULL, " +
                             "password VARCHAR(255) NOT NULL" +
                             ")";
                stmt.executeUpdate(sql);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }

    @Override
    public Connection openConnection() {
        try {
            // Load MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(url, username, password);

        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MySqlConnector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    @Override
    public ResultSet runQuery(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }

    @Override
    public int executeUpdate(Connection conn, String query) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(query);
    }
}